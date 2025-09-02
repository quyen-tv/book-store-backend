package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.AvatarRequest;
import com.quyentv.bookstorebackend.dto.request.UserCreationRequest;
import com.quyentv.bookstorebackend.dto.request.UserUpdateRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.UserResponse;
import com.quyentv.bookstorebackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller", description = "Endpoints for managing users")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @Operation(summary = "Create a new user", description = "Create a new user account.")
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("User created successfully!")
                .result(userService.createUser(request))
                .build();
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users.")
    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .message("Users fetched successfully!")
                .result(userService.getUsers())
                .build();
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by their ID.")
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .message("User fetched successfully!")
                .result(userService.getUser(userId))
                .build();
    }

    @Operation(
            summary = "Get current user's info",
            description = "Retrieve the information of the currently authenticated user.")
    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .message("Your information fetched successfully!")
                .result(userService.getMyInfo())
                .build();
    }

    @Operation(summary = "Delete a user", description = "Delete a user by their ID.")
    @DeleteMapping("/{userId}")
    ApiResponse<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<Void>builder()
                .message("User has been deleted successfully!")
                .build();
    }

    @Operation(
            summary = "Update current user's info",
            description = "Update the information of the currently authenticated user.")
    @PutMapping
    ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("User updated successfully!")
                .result(userService.updateUser(request))
                .build();
    }

    @Operation(
            summary = "Upload user avatar",
            description = "Upload or update the avatar for the currently authenticated user.")
    @PatchMapping(value = "/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<Void> uploadAvatar(@ModelAttribute @Valid AvatarRequest request) {
        userService.uploadAvatar(request.getFile());
        return ApiResponse.<Void>builder()
                .message("User avatar uploaded successfully!")
                .build();
    }
}
