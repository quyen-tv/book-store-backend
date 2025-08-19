package com.quyentv.bookstorebackend.service.impl;

import com.quyentv.bookstorebackend.dto.request.CategoryCreationRequest;
import com.quyentv.bookstorebackend.dto.response.CategoryResponse;
import com.quyentv.bookstorebackend.entity.Category;
import com.quyentv.bookstorebackend.mapper.CategoryMapper;
import com.quyentv.bookstorebackend.repository.CategoryRepository;
import com.quyentv.bookstorebackend.service.CategoryService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryCreationRequest request) {
        Category category = categoryMapper.toCategory(request);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toCategoryResponse).toList();
    }

    @Override
    public void deleteCategory(Long id) {categoryRepository.deleteById(id);}
}
