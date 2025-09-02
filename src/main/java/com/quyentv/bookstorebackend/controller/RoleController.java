package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.RoleRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.RoleResponse;
import com.quyentv.bookstorebackend.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Role Controller", description = "Endpoints for managing roles")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @Operation(summary = "Create a new role", description = "Create a new user role with a set of permissions.")
    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody @Valid RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .message("Role created successfully!")
                .build();
    }

    @Operation(summary = "Get all roles", description = "Retrieve a list of all user roles.")
    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .message("Roles fetched successfully!")
                .result(roleService.getAll())
                .build();
    }

    @Operation(summary = "Delete a role", description = "Delete a role by its name.")
    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .message("Role has been deleted successfully!")
                .build();
    }
}
