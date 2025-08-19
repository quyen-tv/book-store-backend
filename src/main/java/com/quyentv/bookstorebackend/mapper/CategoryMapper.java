package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.request.CategoryCreationRequest;
import com.quyentv.bookstorebackend.dto.response.CategoryResponse;
import com.quyentv.bookstorebackend.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "books", ignore = true)
    Category toCategory(CategoryCreationRequest request);

    CategoryResponse toCategoryResponse(Category category);
}
