package com.quyentv.bookstorebackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

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

    // ================== File related ==================
    INVALID_FILE(400, "File cannot be null or empty", HttpStatus.BAD_REQUEST),

    // ================== Category related ==================
    CATEGORY_NOT_EXISTED(409, "Category {id} not existed", HttpStatus.BAD_REQUEST),
    CATEGORY_ID_MUST_BE_POSITIVE(400, "Category id must be positive", HttpStatus.BAD_REQUEST),

    // ================== Book related ==================
    PRICE_MUST_BE_POSITIVE(400, "Price must be positive", HttpStatus.BAD_REQUEST),
    STOCK_MUST_BE_POSITIVE(400, "Stock must be positive", HttpStatus.BAD_REQUEST),
    PUBLICATION_DATE_CANNOT_BE_IN_FUTURE(400, "Publication date cannot be in future", HttpStatus.BAD_REQUEST),
    DISCOUNT_MUST_BE_POSITIVE(400, "Discount must be positive", HttpStatus.BAD_REQUEST),
    DUPLICATE_PRIMARY_IMAGE(400, "Duplicate primary image", HttpStatus.BAD_REQUEST),
    BOOK_NOT_EXISTED(404, "Book not existed", HttpStatus.NOT_FOUND),
    BOOK_EXISTED(409, "Book existed", HttpStatus.BAD_REQUEST),

    // ================== Cloudinary related ==================
    IMAGE_UPLOADED_FAILED(409, "Image uploaded failed", HttpStatus.BAD_REQUEST),

    // ================== Validation: Required fields ==================
    USERNAME_IS_REQUIRED(400, "Username is required", HttpStatus.BAD_REQUEST),
    PASSWORD_IS_REQUIRED(400, "Password is required", HttpStatus.BAD_REQUEST),
    NAME_IS_REQUIRED(400, "Name is required", HttpStatus.BAD_REQUEST),
    DESCRIPTION_IS_REQUIRED(400, "Description is required", HttpStatus.BAD_REQUEST),
    PERMISSIONS_IS_REQUIRED(400, "Permissions is required", HttpStatus.BAD_REQUEST),
    ROLES_IS_REQUIRED(400, "Roles is required", HttpStatus.BAD_REQUEST),
    TITLE_IS_REQUIRED(400, "Tile is required", HttpStatus.BAD_REQUEST),
    AUTHOR_IS_REQUIRED(400, "Author is required", HttpStatus.BAD_REQUEST),
    PRICE_IS_REQUIRED(400, "Price is required", HttpStatus.BAD_REQUEST),
    STOCK_IS_REQUIRED(400, "Stock is required", HttpStatus.BAD_REQUEST),
    CATEGORIES_IS_REQUIRED(400, "Categories is required", HttpStatus.BAD_REQUEST),
    IMAGES_IS_REQUIRED(400, "Images is required", HttpStatus.BAD_REQUEST),
    IMAGE_URL_IS_REQUIRED(400, "Image url is required", HttpStatus.BAD_REQUEST),
    ISBN_IS_REQUIRED(400, "Isbn is required", HttpStatus.BAD_REQUEST),
    PUBLISHER_IS_REQUIRED(400, "Publisher is required", HttpStatus.BAD_REQUEST),
    PUBLICATION_DATE_IS_REQUIRED(400, "Publication date is required", HttpStatus.BAD_REQUEST),
    FORMAT_IS_REQUIRED(400, "Format is required", HttpStatus.BAD_REQUEST),
    PRIMARY_IMAGE_IS_REQUIRED(400, "Primary image is required", HttpStatus.BAD_REQUEST),
    IS_PRIMARY_IS_REQUIRED(400, "Is primary is required", HttpStatus.BAD_REQUEST),

    // ================== Validation: Non-blank/null ==================
    PERMISSION_CANNOT_BE_BLANK(400, "Permission can not be blank", HttpStatus.BAD_REQUEST),
    ROLE_CANNOT_BE_BLANK(400, "Role can not be blank", HttpStatus.BAD_REQUEST),
    IMAGE_URL_CANNOT_BE_BLANK(400, "Image url can not be blank", HttpStatus.BAD_REQUEST),
    IMAGE_CANNOT_BE_NULL(400, "Image can not be null", HttpStatus.BAD_REQUEST),
    CATEGORY_ID_CANNOT_BE_NULL(400, "Category id can not be null", HttpStatus.BAD_REQUEST),

    // ================== Validation: Invalid values ==================
    INVALID_PERMISSIONS(400, "Permissions must have at least 1", HttpStatus.BAD_REQUEST),
    INVALID_ROLES(400, "Roles must have at least 1", HttpStatus.BAD_REQUEST),
    INVALID_CATEGORIES(400, "Categories must have at least 1", HttpStatus.BAD_REQUEST),
    INVALID_IMAGES(400, "Images must have at least 1", HttpStatus.BAD_REQUEST),
    ISBN_INVALID_LENGTH(400, "ISBN length must be between 10 and 13 characters", HttpStatus.BAD_REQUEST),
    ISBN_INVALID_FORMAT(400, "ISBN must contain only digits (10 or 13 characters)", HttpStatus.BAD_REQUEST),

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
