package com.example.service;

import com.example.model.Book;
import com.example.model.BookDTO;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book add(BookDTO request) {
        Book book = Book.builder()
                .author(request.author())
                .title(request.title())
                .pages(request.pages())
                .build();

        return bookRepository.save(book);
    }

    public Collection<Book> findAll(Map<String, String> filters) {
        return bookRepository.findAll(filters);
    }
}
