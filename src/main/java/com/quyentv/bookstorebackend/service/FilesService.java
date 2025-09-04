package com.quyentv.bookstorebackend.service;

import com.quyentv.bookstorebackend.dto.request.FileUploadRequest;
import java.util.List;

public interface FilesService {

    List<String> uploadFiles(FileUploadRequest request);
}
