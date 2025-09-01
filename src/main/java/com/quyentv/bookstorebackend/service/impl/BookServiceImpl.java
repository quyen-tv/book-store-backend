package com.quyentv.bookstorebackend.service.impl;

import com.quyentv.bookstorebackend.dto.request.BookImageRequest;
import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public BookResponse createBook(BookRequest request) {
        validateImage(request.getImages());
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
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toBookResponse).toList();
    }

    @Override
    public void deleteBook(Long bookId) {
        boolean isExisted = bookRepository.existsById(bookId);
        if (!isExisted) throw new AppException(ErrorCode.BOOK_NOT_EXISTED);
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookResponse updateBook(Long bookId, BookRequest request) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        validateImage(request.getImages());
        bookMapper.updateBook(book, request);
        populateCategoriesAndImages(request, book);
        bookRepository.save(book);
        return bookMapper.toBookResponse(book);
    }

    private void validateImage(Set<BookImageRequest> images) {
        long primaryCount =
                images.stream().filter(BookImageRequest::getIsPrimary).count();
        if (primaryCount > 1) throw new AppException(ErrorCode.DUPLICATE_PRIMARY_IMAGE);
        if (primaryCount == 0) throw new AppException(ErrorCode.PRIMARY_IMAGE_IS_REQUIRED);
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

    private Set<BookImage> resolveBookImages(Set<BookImageRequest> images) {
        return images.stream().map(bookImageMapper::toBookImage).collect(Collectors.toSet());
    }
}
