package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.request.BookImageRequest;
import com.quyentv.bookstorebackend.dto.response.BookImageResponse;
import com.quyentv.bookstorebackend.entity.BookImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookImageMapper {

    BookImage toBookImage(BookImageRequest request);

    BookImageResponse toBookImageResponse(BookImage bookImage);
}
