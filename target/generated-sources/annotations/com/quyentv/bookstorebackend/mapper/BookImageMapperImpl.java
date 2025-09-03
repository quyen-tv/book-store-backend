package com.quyentv.bookstorebackend.mapper;

import com.quyentv.bookstorebackend.dto.response.BookImageResponse;
import com.quyentv.bookstorebackend.entity.BookImage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class BookImageMapperImpl implements BookImageMapper {

    @Override
    public BookImage toBookImage(String url) {
        if ( url == null ) {
            return null;
        }

        BookImage.BookImageBuilder bookImage = BookImage.builder();

        bookImage.url( url );

        return bookImage.build();
    }

    @Override
    public BookImageResponse toBookImageResponse(BookImage bookImage) {
        if ( bookImage == null ) {
            return null;
        }

        BookImageResponse bookImageResponse = new BookImageResponse();

        bookImageResponse.setId( bookImage.getId() );
        bookImageResponse.setUrl( bookImage.getUrl() );

        return bookImageResponse;
    }
}
