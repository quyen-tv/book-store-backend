package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.AvatarRequest;
import com.quyentv.bookstorebackend.dto.request.UserCreationRequest;
import com.quyentv.bookstorebackend.dto.request.UserUpdateRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.UserResponse;
import com.quyentv.bookstorebackend.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("User created successfully!")
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .message("Users fetched successfully!")
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .message("User fetched successfully!")
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .message("Your information fetched successfully!")
                .result(userService.getMyInfo())
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<Void>builder()
                .message("User has been deleted successfully!")
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("User updated successfully!")
                .result(userService.updateUser(userId, request))
                .build();
    }

    @PatchMapping(value = "/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<Void> uploadAvatar(@ModelAttribute @Valid AvatarRequest request) {
        userService.uploadAvatar(request.getFile());
        return ApiResponse.<Void>builder()
                .message("User avatar uploaded successfully!")
                .build();
    }
}
