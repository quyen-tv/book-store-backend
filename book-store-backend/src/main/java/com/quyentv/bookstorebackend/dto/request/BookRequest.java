package com.quyentv.bookstorebackend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {

    @NotBlank(message = "TITLE_IS_REQUIRED")
    String title;

    @NotBlank(message = "AUTHOR_IS_REQUIRED")
    String author;

    @NotBlank(message = "DESCRIPTION_IS_REQUIRED")
    String description;

    @NotNull(message = "PRICE_IS_REQUIRED")
    @PositiveOrZero(message = "PRICE_MUST_BE_POSITIVE")
    Double price;

    @NotNull(message = "STOCK_IS_REQUIRED")
    @PositiveOrZero(message = "STOCK_MUST_BE_POSITIVE")
    Integer stock;

    @NotNull(message = "CATEGORIES_IS_REQUIRED")
    @Size(min = 1, message = "INVALID_CATEGORIES")
    Set<@NotNull(message = "CATEGORY_ID_CANNOT_BE_NULL") @Positive(message = "CATEGORY_ID_MUST_BE_POSITIVE") Long>
            categoryIds;

    @NotNull(message = "IMAGES_IS_REQUIRED")
    @Size(min = 1, message = "INVALID_IMAGES")
    Set<@Valid @NotNull(message = "IMAGE_CANNOT_BE_NULL") BookImageRequest> images;

    @NotNull(message = "ISBN_IS_REQUIRED")
    @Size(min = 10, max = 13, message = "ISBN_INVALID_LENGTH")
    @Pattern(regexp = "\\d{10}|\\d{13}", message = "ISBN_INVALID_FORMAT")
    String isbn;

    @NotBlank(message = "PUBLISHER_IS_REQUIRED")
    String publisher;

    @NotNull(message = "PUBLICATION_DATE_IS_REQUIRED")
    @PastOrPresent(message = "PUBLICATION_DATE_CANNOT_BE_IN_FUTURE")
    LocalDate publicationDate;

    Integer pageCount;

    String language;

    @NotBlank(message = "FORMAT_IS_REQUIRED")
    String format;

    @PositiveOrZero(message = "DISCOUNT_MUST_BE_POSITIVE")
    Double discount;
}
