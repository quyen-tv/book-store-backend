package com.quyentv.bookstorebackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.quyentv.bookstorebackend.exception.AppException;
import com.quyentv.bookstorebackend.exception.ErrorCode;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file, String folder) {
        try {
            var result = cloudinary
                    .uploader()
                    .upload(
                            file.getBytes(),
                            ObjectUtils.asMap(
                                    "folder",
                                    folder,
                                    "use_filename",
                                    true,
                                    "unique_filename",
                                    true,
                                    "resource_type",
                                    "auto"));
            return result.get("secure_url").toString();
        } catch (IOException io) {
            throw new AppException(ErrorCode.IMAGE_UPLOADED_FAILED);
        }
    }
}
