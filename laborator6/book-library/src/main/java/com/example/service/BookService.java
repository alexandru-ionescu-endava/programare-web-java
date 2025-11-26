package com.example.service;

import com.example.model.Book;
import com.example.model.BookDTO;
import com.example.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Slf4j
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

        return bookRepository.saveOrUpdate(book);
    }

    public Collection<Book> findAll(Map<String, String> filters) {
        log.info("findAll called");
        return bookRepository.findAll(filters);
    }

    public Book replaceBook(Integer id,
                            BookDTO request) {

        Book replaced = Book.builder()
                .id(id)
                .author(request.author())
                .title(request.title())
                .pages(request.pages())
                .build();

        return bookRepository.saveOrUpdate(replaced);
    }

    public Book patchBook(Integer id,
                          BookDTO request) {

        log.info("patchBook start for id : {}", id);
        log.debug("patchBook debug");

        Book existing = bookRepository.findById(id)
                .orElse(null);

        if (existing == null) return null;

        if (request.title() != null)
            existing.setTitle(request.title());

        if (request.pages() != null)
            existing.setPages(request.pages());

        if (request.author() != null)
            existing.setAuthor(request.author());

        log.info("patchBook end");

        return bookRepository.saveOrUpdate(existing);
    }
}
