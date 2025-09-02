package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.CategoryCreationRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.CategoryResponse;
import com.quyentv.bookstorebackend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Category Controller", description = "Endpoints for managing categories")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @Operation(summary = "Create a new category", description = "Create a new book category.")
    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryCreationRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Category created successfully!")
                .result(categoryService.createCategory(request))
                .build();
    }

    @Operation(summary = "Get all categories", description = "Retrieve a list of all book categories.")
    @GetMapping
    ApiResponse<List<CategoryResponse>> getCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .message("Categories fetched successfully!")
                .result(categoryService.getCategories())
                .build();
    }

    @Operation(summary = "Delete a category", description = "Delete a category by its ID.")
    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<Void>builder()
                .message("Category deleted successfully!")
                .build();
    }
}
