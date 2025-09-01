package com.quyentv.bookstorebackend.controller;

import com.nimbusds.jose.JOSEException;
import com.quyentv.bookstorebackend.dto.request.AuthenticationRequest;
import com.quyentv.bookstorebackend.dto.request.IntrospectRequest;
import com.quyentv.bookstorebackend.dto.request.LogoutRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.AuthenticationResponse;
import com.quyentv.bookstorebackend.dto.response.IntrospectResponse;
import com.quyentv.bookstorebackend.service.AuthenticationService;
import com.quyentv.bookstorebackend.service.impl.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.text.ParseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request, HttpServletResponse response) {
        var result = authenticationService.authenticate(request, response);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Authentication successful!")
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody @Valid IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody @Valid LogoutRequest request, @CookieValue String refreshToken) throws ParseException, JOSEException {
        authenticationService.logout(request, refreshToken);
        return ApiResponse.<Void>builder().message("Logged out successfully!").build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refreshToken(@CookieValue String refreshToken, HttpServletResponse response) {
        var result = authenticationService.refreshToken(refreshToken, response);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Token refreshed successfully!")
                .result(result)
                .build();
    }
}
