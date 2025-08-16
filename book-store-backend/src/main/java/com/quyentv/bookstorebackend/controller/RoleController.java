package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.RoleRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.RoleResponse;
import com.quyentv.bookstorebackend.service.impl.RoleServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleServiceImpl roleServiceImpl;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody @Valid RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleServiceImpl.create(request))
                .message("Role created successfully!")
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .message("Roles fetched successfully!")
                .result(roleServiceImpl.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleServiceImpl.delete(role);
        return ApiResponse.<Void>builder()
                .message("Role has been deleted successfully!")
                .build();
    }
}
