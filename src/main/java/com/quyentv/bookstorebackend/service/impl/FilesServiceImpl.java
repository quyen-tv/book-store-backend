package com.quyentv.bookstorebackend.service.impl;

import com.quyentv.bookstorebackend.dto.request.FileUploadRequest;
import com.quyentv.bookstorebackend.service.CloudinaryService;
import com.quyentv.bookstorebackend.service.FilesService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilesServiceImpl implements FilesService {

    CloudinaryService cloudinaryService;

    static String FOLDER = "/book-store/books";

    @PreAuthorize("isAuthenticated()")
    @Override
    public List<String> uploadFiles(FileUploadRequest request) {
        return request.getFiles().stream()
                .map(file -> cloudinaryService.uploadImage(file, FOLDER))
                .toList();
    }
}
