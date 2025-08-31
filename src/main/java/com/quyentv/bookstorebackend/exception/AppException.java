package com.quyentv.bookstorebackend.exception;

import java.util.Map;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Map<String, Object> attributes;

    public AppException(ErrorCode errorCode) {
        this(errorCode, Map.of());
    }

    public AppException(ErrorCode errorCode, Map<String, Object> attributes) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.attributes = attributes;
    }
}
