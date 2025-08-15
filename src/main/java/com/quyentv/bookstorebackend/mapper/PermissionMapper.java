package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.request.PermissionRequest;
import com.quyentv.bookstorebackend.dto.response.PermissionResponse;
import com.quyentv.bookstorebackend.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}

