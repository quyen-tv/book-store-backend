package com.quyentv.bookstorebackend.dto.request;

import com.quyentv.bookstorebackend.validator.NotEmptyFile;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileUploadRequest {

    @NotNull(message = "FILES_IS_REQUIRED")
    @Size(min = 1, message = "INVALID_FILES")
    private List<@NotEmptyFile(message = "INVALID_FILE") MultipartFile> files;
}
