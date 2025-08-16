package com.quyentv.bookstorebackend.service;

import com.nimbusds.jose.JOSEException;
import com.quyentv.bookstorebackend.dto.request.AuthenticationRequest;
import com.quyentv.bookstorebackend.dto.request.IntrospectRequest;
import com.quyentv.bookstorebackend.dto.request.LogoutRequest;
import com.quyentv.bookstorebackend.dto.request.RefreshRequest;
import com.quyentv.bookstorebackend.dto.response.AuthenticationResponse;
import com.quyentv.bookstorebackend.dto.response.IntrospectResponse;
import com.quyentv.bookstorebackend.exception.AppException;
import com.quyentv.bookstorebackend.exception.ErrorCode;
import com.quyentv.bookstorebackend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    RedisService redisService;

    private static final String BLACKLIST_PREFIX = "jti:";
    private static final String REFRESH_PREFIX = "refresh:";

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            jwtService.verifyAccessToken(token);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {

        try {
            var accessToken = request.getAccessToken();
            var signToken = jwtService.verifyAccessToken(accessToken);

            String jti = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
            long expiresInSeconds = (expiryTime.getTime() - new Date().getTime()) / 1000;
            if (expiresInSeconds > 0) {
                redisService.save(BLACKLIST_PREFIX + jti, "revoked", expiresInSeconds, TimeUnit.SECONDS);
            }
        } catch (AppException exception) {
            log.info("Token already expired");
        }

        var refreshToken = request.getRefreshToken();
        redisService.delete(REFRESH_PREFIX + refreshToken);
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) {
        var refreshToken = request.getRefreshToken();
        if (!redisService.hasKey(REFRESH_PREFIX + refreshToken)) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var username = (String) redisService.get(REFRESH_PREFIX + refreshToken);
        if (username == null) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        redisService.delete(REFRESH_PREFIX + refreshToken);

        var newAccessToken = jwtService.generateAccessToken(user);
        var newRefreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .authenticated(true)
                .build();
    }
}
