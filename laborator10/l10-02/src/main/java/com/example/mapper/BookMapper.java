package com.example.mapper;

import com.example.model.dto.BookResponse;
import com.example.model.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponse fromEntity(Book book) {
        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setPages(book.getPages());

        if (book.getBookDetail() != null) {
            response.setPublicationYear(book.getBookDetail().getPublicationYear());
            response.setFormatType(book.getBookDetail().getFormatType());
        }

        if (book.getAuthor() != null) {
            response.setAuthorName(book.getAuthor().getName());
        }

        return response;
    }



}
