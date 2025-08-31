package com.quyentv.bookstorebackend.dto.request;

import com.quyentv.bookstorebackend.validator.NotEmptyFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AvatarRequest {

    @NotEmptyFile(message = "INVALID_FILE")
    private MultipartFile file;
}
