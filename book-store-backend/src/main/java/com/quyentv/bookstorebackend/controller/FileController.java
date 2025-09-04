package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.FileUploadRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.service.FilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "File Controller", description = "Endpoints for upload files")
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileController {

    FilesService filesService;

    @Operation(
            summary = "Upload multiple image files",
            description = "Upload one or more image files and return their URLs")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<List<String>> uploadFiles(@ModelAttribute @Valid FileUploadRequest request) {
        return ApiResponse.<List<String>>builder()
                .message("Image files uploaded successfully!")
                .result(filesService.uploadFiles(request))
                .build();
    }
}
