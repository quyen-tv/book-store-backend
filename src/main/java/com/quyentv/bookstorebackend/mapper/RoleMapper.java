package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.request.RoleRequest;
import com.quyentv.bookstorebackend.dto.response.RoleResponse;
import com.quyentv.bookstorebackend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
