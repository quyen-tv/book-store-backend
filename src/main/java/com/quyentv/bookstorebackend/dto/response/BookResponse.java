package com.quyentv.bookstorebackend.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {

    Long id;
    String title;
    String author;
    String description;
    Double price;
    Integer stock;
    Set<CategoryResponse> categories;
    String thumbnail;
    Set<BookImageResponse> images;
    String isbn;
    String publisher;
    LocalDate publicationDate;
    Integer pageCount;
    String language;
    String format;
    Double discount;
    Integer soldCount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
