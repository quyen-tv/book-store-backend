package com.quyentv.bookstorebackend.service.impl;

import com.quyentv.bookstorebackend.dto.request.BookFilter;
import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
import com.quyentv.bookstorebackend.dto.response.PageResponse;
import com.quyentv.bookstorebackend.entity.Book;
import com.quyentv.bookstorebackend.entity.BookImage;
import com.quyentv.bookstorebackend.entity.Category;
import com.quyentv.bookstorebackend.exception.AppException;
import com.quyentv.bookstorebackend.exception.ErrorCode;
import com.quyentv.bookstorebackend.mapper.BookImageMapper;
import com.quyentv.bookstorebackend.mapper.BookMapper;
import com.quyentv.bookstorebackend.repository.BookRepository;
import com.quyentv.bookstorebackend.repository.CategoryRepository;
import com.quyentv.bookstorebackend.service.BookService;
import com.quyentv.bookstorebackend.specification.BookSpecification;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    BookMapper bookMapper;
    BookImageMapper bookImageMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public BookResponse createBook(BookRequest request) {
        Book book = bookMapper.toBook(request);
        populateCategoriesAndImages(request, book);

        try {
            bookRepository.save(book);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.BOOK_EXISTED);
        }

        return bookMapper.toBookResponse(book);
    }

    @Override
    public PageResponse<BookResponse> getAllBooks(int page, int limit, String sort, BookFilter filter) {

        Sort sortObj = Sort.by("id").descending();
        if (sort != null && !sort.isBlank()) {
            String[] parts = sort.split(",");
            sortObj = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
        }

        Pageable pageable = PageRequest.of(page, limit, sortObj);

        Specification<Book> spec = BookSpecification.titleContains(filter.getTitle())
                .and(BookSpecification.authorContains(filter.getAuthor()))
                .and(BookSpecification.priceGreaterThanOrEq(filter.getPriceMin()))
                .and(BookSpecification.priceLessThanOrEq(filter.getPriceMax()))
                .and(BookSpecification.categoryEquals(filter.getCategory()));

        Page<Book> bookPage = bookRepository.findAll(spec, pageable);

        List<BookResponse> items =
                bookPage.getContent().stream().map(bookMapper::toBookResponse).toList();

        return PageResponse.<BookResponse>builder()
                .items(items)
                .page(bookPage.getNumber())
                .limit(bookPage.getSize())
                .totalItems((int) bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .hasNextPage(bookPage.hasNext())
                .hasPrevPage(bookPage.hasPrevious())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteBook(Long bookId) {
        boolean isExisted = bookRepository.existsById(bookId);
        if (!isExisted) throw new AppException(ErrorCode.BOOK_NOT_EXISTED);
        bookRepository.deleteById(bookId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public BookResponse updateBook(Long bookId, BookRequest request) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        bookMapper.updateBook(book, request);
        populateCategoriesAndImages(request, book);
        bookRepository.save(book);
        return bookMapper.toBookResponse(book);
    }

    @Override
    public BookResponse getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        return bookMapper.toBookResponse(book);
    }

    private void populateCategoriesAndImages(BookRequest request, Book book) {
        Set<Category> categories = resolveCategories(request.getCategoryIds());
        book.getCategories().clear();
        book.getCategories().addAll(categories);

        Set<BookImage> bookImages = resolveBookImages(request.getImages());
        book.getImages().clear();
        book.getImages().addAll(bookImages);
    }

    private Set<Category> resolveCategories(Set<Long> categoryIds) {
        return categoryIds.stream()
                .map(categoryId -> categoryRepository
                        .findById(categoryId)
                        .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED, Map.of("id", categoryId))))
                .collect(Collectors.toSet());
    }

    private Set<BookImage> resolveBookImages(Set<String> images) {
        return images.stream().map(bookImageMapper::toBookImage).collect(Collectors.toSet());
    }
}
