package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
import com.quyentv.bookstorebackend.service.BookService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService bookService;

    @PostMapping
    ApiResponse<BookResponse> createBook(@RequestBody @Valid BookRequest request) {
        return ApiResponse.<BookResponse>builder()
                .message("Book created successfully!")
                .result(bookService.createBook(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<BookResponse>> getAllBooks() {
        return ApiResponse.<List<BookResponse>>builder()
                .message("Books fetched successfully!")
                .result(bookService.getAllBooks())
                .build();
    }

    @PutMapping("/{bookId}")
    ApiResponse<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody @Valid BookRequest request) {
        return ApiResponse.<BookResponse>builder()
                .message("Book updated successfully!")
                .result(bookService.updateBook(bookId, request))
                .build();
    }

    @DeleteMapping("/{bookId}")
    ApiResponse<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ApiResponse.<Void>builder()
                .message("Book has been deleted successfully!")
                .build();
    }
}
