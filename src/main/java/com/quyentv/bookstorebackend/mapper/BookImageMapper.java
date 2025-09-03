package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.response.BookImageResponse;
import com.quyentv.bookstorebackend.entity.BookImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookImageMapper {

    @Mapping(target = "url", source = "url")
    @Mapping(target = "id", ignore = true)
    BookImage toBookImage(String url);

    BookImageResponse toBookImageResponse(BookImage bookImage);
}
