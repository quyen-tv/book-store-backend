package com.quyentv.bookstorebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {
    private String title;
    private String author;
    private String category;
    private Double priceMin;
    private Double priceMax;
}
