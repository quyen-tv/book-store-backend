package com.quyentv.bookstorebackend.service;

import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
import java.util.List;

public interface BookService {

    BookResponse createBook(BookRequest request);

    List<BookResponse> getAllBooks();

    void deleteBook(Long bookId);

    BookResponse updateBook(Long bookId, BookRequest request);
}
