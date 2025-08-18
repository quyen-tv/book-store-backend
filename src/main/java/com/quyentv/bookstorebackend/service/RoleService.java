package com.quyentv.bookstorebackend.service;

import com.quyentv.bookstorebackend.dto.request.RoleRequest;
import com.quyentv.bookstorebackend.dto.response.RoleResponse;
import java.util.List;

public interface RoleService {

    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(String role);
}
