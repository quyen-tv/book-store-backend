package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.request.BookRequest;
import com.quyentv.bookstorebackend.dto.response.BookImageResponse;
import com.quyentv.bookstorebackend.dto.response.BookResponse;
import com.quyentv.bookstorebackend.dto.response.CategoryResponse;
import com.quyentv.bookstorebackend.entity.Book;
import com.quyentv.bookstorebackend.entity.BookImage;
import com.quyentv.bookstorebackend.entity.Category;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BookImageMapper bookImageMapper;

    @Override
    public Book toBook(BookRequest request) {
        if ( request == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.title( request.getTitle() );
        book.author( request.getAuthor() );
        book.description( request.getDescription() );
        book.price( request.getPrice() );
        book.stock( request.getStock() );
        book.thumbnail( request.getThumbnail() );
        book.isbn( request.getIsbn() );
        book.publisher( request.getPublisher() );
        book.publicationDate( request.getPublicationDate() );
        book.pageCount( request.getPageCount() );
        book.language( request.getLanguage() );
        book.format( request.getFormat() );
        book.discount( request.getDiscount() );

        return book.build();
    }

    @Override
    public BookResponse toBookResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponse bookResponse = new BookResponse();

        bookResponse.setCategories( categorySetToCategoryResponseSet( book.getCategories() ) );
        bookResponse.setImages( bookImageSetToBookImageResponseSet( book.getImages() ) );
        bookResponse.setId( book.getId() );
        bookResponse.setTitle( book.getTitle() );
        bookResponse.setAuthor( book.getAuthor() );
        bookResponse.setDescription( book.getDescription() );
        bookResponse.setPrice( book.getPrice() );
        bookResponse.setStock( book.getStock() );
        bookResponse.setThumbnail( book.getThumbnail() );
        bookResponse.setIsbn( book.getIsbn() );
        bookResponse.setPublisher( book.getPublisher() );
        bookResponse.setPublicationDate( book.getPublicationDate() );
        bookResponse.setPageCount( book.getPageCount() );
        bookResponse.setLanguage( book.getLanguage() );
        bookResponse.setFormat( book.getFormat() );
        bookResponse.setDiscount( book.getDiscount() );
        bookResponse.setSoldCount( book.getSoldCount() );
        bookResponse.setCreatedAt( book.getCreatedAt() );
        bookResponse.setUpdatedAt( book.getUpdatedAt() );

        return bookResponse;
    }

    @Override
    public void updateBook(Book book, BookRequest request) {
        if ( request == null ) {
            return;
        }

        book.setTitle( request.getTitle() );
        book.setAuthor( request.getAuthor() );
        book.setDescription( request.getDescription() );
        book.setPrice( request.getPrice() );
        book.setStock( request.getStock() );
        book.setThumbnail( request.getThumbnail() );
        book.setIsbn( request.getIsbn() );
        book.setPublisher( request.getPublisher() );
        book.setPublicationDate( request.getPublicationDate() );
        book.setPageCount( request.getPageCount() );
        book.setLanguage( request.getLanguage() );
        book.setFormat( request.getFormat() );
        book.setDiscount( request.getDiscount() );
    }

    protected Set<CategoryResponse> categorySetToCategoryResponseSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryResponse> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Category category : set ) {
            set1.add( categoryMapper.toCategoryResponse( category ) );
        }

        return set1;
    }

    protected Set<BookImageResponse> bookImageSetToBookImageResponseSet(Set<BookImage> set) {
        if ( set == null ) {
            return null;
        }

        Set<BookImageResponse> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( BookImage bookImage : set ) {
            set1.add( bookImageMapper.toBookImageResponse( bookImage ) );
        }

        return set1;
    }
}
