package com.quyentv.bookstorebackend.dto.request;

import jakarta.validation.constraints.NotBlank;
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
}
