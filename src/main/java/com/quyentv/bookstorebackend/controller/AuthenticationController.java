package com.quyentv.bookstorebackend.controller;

import com.nimbusds.jose.JOSEException;
import com.quyentv.bookstorebackend.dto.request.AuthenticationRequest;
import com.quyentv.bookstorebackend.dto.request.IntrospectRequest;
import com.quyentv.bookstorebackend.dto.request.LogoutRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.AuthenticationResponse;
import com.quyentv.bookstorebackend.dto.response.IntrospectResponse;
import com.quyentv.bookstorebackend.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.text.ParseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Controller", description = "Endpoints for authentication")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @Operation(summary = "Authenticate user", description = "Authenticate with username and password to get tokens.")
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request, HttpServletResponse response) {
        var result = authenticationService.authenticate(request, response);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Authentication successful!")
                .result(result)
                .build();
    }

    @Operation(summary = "Introspect token", description = "Verify the validity of a token.")
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody @Valid IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @Operation(summary = "Logout", description = "Log out by invalidating the token.")
    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody @Valid LogoutRequest request, @CookieValue String refreshToken)
            throws ParseException, JOSEException {
        authenticationService.logout(request, refreshToken);
        return ApiResponse.<Void>builder().message("Logged out successfully!").build();
    }

    @Operation(summary = "Refresh token", description = "Get a new access token using a refresh token.")
    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refreshToken(@CookieValue String refreshToken, HttpServletResponse response) {
        var result = authenticationService.refreshToken(refreshToken, response);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Token refreshed successfully!")
                .result(result)
                .build();
    }
}
