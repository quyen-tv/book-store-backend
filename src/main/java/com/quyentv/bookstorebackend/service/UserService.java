package com.quyentv.bookstorebackend.service;

import com.quyentv.bookstorebackend.dto.request.UserCreationRequest;
import com.quyentv.bookstorebackend.dto.request.UserUpdateRequest;
import com.quyentv.bookstorebackend.dto.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreationRequest request);

    UserResponse updateUser(String userId, UserUpdateRequest request);

    UserResponse getMyInfo();

    void deleteUser(String userId);

    List<UserResponse> getUsers();

    UserResponse getUser(String id);

    void uploadAvatar(MultipartFile file, String folder);
}
