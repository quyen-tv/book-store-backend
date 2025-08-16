package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.PermissionRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.PermissionResponse;
import com.quyentv.bookstorebackend.service.impl.PermissionServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionServiceImpl permissionServiceImpl;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody @Valid PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionServiceImpl.create(request))
                .message("Permission created successfully!")
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .message("Permissions fetched successfully!")
                .result(permissionServiceImpl.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permissionServiceImpl.delete(permission);
        return ApiResponse.<Void>builder()
                .message("Permission has been deleted successfully!")
                .build();
    }
}
