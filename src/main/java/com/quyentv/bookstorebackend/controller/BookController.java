package com.quyentv.bookstorebackend.controller;

import com.quyentv.bookstorebackend.dto.request.BookFilter;
import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.ApiResponse;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
import com.quyentv.bookstorebackend.dto.response.PageResponse;
import com.quyentv.bookstorebackend.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book Controller", description = "Endpoints for managing books")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService bookService;

    @Operation(summary = "Create a new book", description = "Create a new book with the provided details.")
    @PostMapping
    ApiResponse<BookResponse> createBook(@RequestBody @Valid BookRequest request) {
        return ApiResponse.<BookResponse>builder()
                .message("Book created successfully!")
                .result(bookService.createBook(request))
                .build();
    }

    @Operation(summary = "Get all books", description = "Retrieve a paginated list of books with optional filters.")
    @GetMapping
    ApiResponse<PageResponse<BookResponse>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax) {
        BookFilter filter = BookFilter.builder()
                .title(title)
                .author(author)
                .category(category)
                .priceMin(priceMin)
                .priceMax(priceMax)
                .build();

        return ApiResponse.<PageResponse<BookResponse>>builder()
                .message("Books fetched successfully!")
                .result(bookService.getAllBooks(page, limit, sort, filter))
                .build();
    }

    @Operation(summary = "Get a book", description = "Get an existing book's details.")
    @GetMapping("/{bookId}")
    ApiResponse<BookResponse> getBook(@PathVariable Long bookId) {
        return ApiResponse.<BookResponse>builder()
                .message("Book information fetched successfully!")
                .result(bookService.getBook(bookId))
                .build();
    }

    @Operation(summary = "Update a book", description = "Update an existing book's details.")
    @PutMapping("/{bookId}")
    ApiResponse<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody @Valid BookRequest request) {
        return ApiResponse.<BookResponse>builder()
                .message("Book updated successfully!")
                .result(bookService.updateBook(bookId, request))
                .build();
    }

    @Operation(summary = "Delete a book", description = "Delete a book by its ID.")
    @DeleteMapping("/{bookId}")
    ApiResponse<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ApiResponse.<Void>builder()
                .message("Book has been deleted successfully!")
                .build();
    }
}
