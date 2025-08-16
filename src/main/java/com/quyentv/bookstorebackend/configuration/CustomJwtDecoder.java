package com.quyentv.bookstorebackend.configuration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.quyentv.bookstorebackend.service.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signer-key}")
    private String signerKey;

    private final JwtService jwtService;
    private NimbusJwtDecoder nimbusJwtDecoder;

    @PostConstruct
    private void init() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), JWSAlgorithm.HS512.toString());
        this.nimbusJwtDecoder = NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Override
    public Jwt decode(String token) throws JwtException {

        try {
            jwtService.verifyAccessToken(token);
            return nimbusJwtDecoder.decode(token);
        } catch (JOSEException | ParseException e) {
            throw new JwtException("Invalid token", e);
        }
    }
}
