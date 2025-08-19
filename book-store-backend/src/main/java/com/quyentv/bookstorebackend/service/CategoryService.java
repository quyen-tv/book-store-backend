package com.quyentv.bookstorebackend.service;

import com.quyentv.bookstorebackend.dto.request.CategoryCreationRequest;
import com.quyentv.bookstorebackend.dto.response.CategoryResponse;
import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryCreationRequest request);

    List<CategoryResponse> getCategories();

    void deleteCategory(Long id);
}
