package com.example.service;

import com.example.exception.BookBatchValidationException;
import com.example.model.Book;
import com.example.model.BookDTO;
import com.example.model.BookUpdateDTO;
import com.example.model.BookValidationError;
import com.example.repository.BookRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

//    @Autowired
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

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

    public List<Book> batchUpdate(List<BookUpdateDTO> requestList) {

        log.info("batchUpdate start for request list : {}", requestList);
        List<Book> updatedList = new ArrayList<>();

        requestList.forEach(bookDTO -> {
            Book book = Book.builder()
                    .id(bookDTO.id())
                    .title(bookDTO.title())
                    .author(bookDTO.author())
                    .pages(bookDTO.pages())
                    .build();

            bookRepository.saveOrUpdate(book);

            updatedList.add(book);
        });

        log.info("successfully updated {} books", requestList.size());

        return updatedList;
    }
}
