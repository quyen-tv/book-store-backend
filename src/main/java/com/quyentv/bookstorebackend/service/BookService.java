package com.quyentv.bookstorebackend.service;

import com.quyentv.bookstorebackend.dto.request.BookFilter;
import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
import com.quyentv.bookstorebackend.dto.response.PageResponse;

public interface BookService {

    BookResponse createBook(BookRequest request);

    PageResponse<BookResponse> getAllBooks(int page, int limit, String sort, BookFilter filter);

    void deleteBook(Long bookId);

    BookResponse updateBook(Long bookId, BookRequest request);

    BookResponse getBook(Long bookId);
}
