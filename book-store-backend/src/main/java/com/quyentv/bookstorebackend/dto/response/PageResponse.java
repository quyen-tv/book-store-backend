package com.quyentv.bookstorebackend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {
    List<T> items;
    int page;
    int limit;
    int totalItems;
    int totalPages;
    boolean hasNextPage;
    boolean hasPrevPage;
}
