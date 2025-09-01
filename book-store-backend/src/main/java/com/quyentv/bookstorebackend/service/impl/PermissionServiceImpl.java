package com.quyentv.bookstorebackend.service.impl;

import com.quyentv.bookstorebackend.dto.request.PermissionRequest;
import com.quyentv.bookstorebackend.dto.response.PermissionResponse;
import com.quyentv.bookstorebackend.entity.Permission;
import com.quyentv.bookstorebackend.mapper.PermissionMapper;
import com.quyentv.bookstorebackend.repository.PermissionRepository;
import com.quyentv.bookstorebackend.service.PermissionService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PreAuthorize("hasRole('ADMIN')")
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
