package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
import com.quyentv.bookstorebackend.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {CategoryMapper.class, BookImageMapper.class})
public interface BookMapper {

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "images", ignore = true)
    Book toBook(BookRequest request);

    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "images", source = "images")
    BookResponse toBookResponse(Book book);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updateBook(@MappingTarget Book book, BookRequest request);
}
