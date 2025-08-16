package com.quyentv.bookstorebackend.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // ================== Authentication & Authorization ==================
    ACCESS_TOKEN_IS_REQUIRED(400, "Access token is required", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_IS_REQUIRED(400, "Refresh token is required", HttpStatus.BAD_REQUEST),
    INVALID_ACCESS_TOKEN(400, "Access token is invalid", HttpStatus.BAD_REQUEST),
    INVALID_REFRESH_TOKEN(400, "Refresh token is invalid", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS(401, "Password is incorrect", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(401, "You need to log in to perform this action.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "You do not have permission", HttpStatus.FORBIDDEN),

    // ================== User related ==================
    USER_EXISTED(409, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(404, "User not existed", HttpStatus.NOT_FOUND),
    INVALID_USERNAME(400, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(400, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_DOB(400, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    // ================== Validation: Required fields ==================
    USERNAME_IS_REQUIRED(400, "Username is required", HttpStatus.BAD_REQUEST),
    PASSWORD_IS_REQUIRED(400, "Password is required", HttpStatus.BAD_REQUEST),
    NAME_IS_REQUIRED(400, "Name is required", HttpStatus.BAD_REQUEST),
    DESCRIPTION_IS_REQUIRED(400, "Description is required", HttpStatus.BAD_REQUEST),
    PERMISSIONS_IS_REQUIRED(400, "Permissions is required", HttpStatus.BAD_REQUEST),
    ROLES_IS_REQUIRED(400, "Roles is required", HttpStatus.BAD_REQUEST),

    // ================== Validation: Non-blank ==================
    PERMISSION_CANNOT_BE_BLANK(400, "Permission can not be blank", HttpStatus.BAD_REQUEST),
    ROLE_CANNOT_BE_BLANK(400, "Role can not be blank", HttpStatus.BAD_REQUEST),

    // ================== Validation: Invalid values ==================
    INVALID_PERMISSIONS(400, "Permissions must have at least 1", HttpStatus.BAD_REQUEST),
    INVALID_ROLES(400, "Roles must have at least 1", HttpStatus.BAD_REQUEST),

    // ================== System ==================
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR);


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}