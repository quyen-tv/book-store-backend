package com.quyentv.bookstorebackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookImageRequest {

    @NotBlank(message = "IMAGE_URL_IS_REQUIRED")
    String url;

    @NotNull(message = "IS_PRIMARY_IS_REQUIRED")
    Boolean isPrimary;
}
