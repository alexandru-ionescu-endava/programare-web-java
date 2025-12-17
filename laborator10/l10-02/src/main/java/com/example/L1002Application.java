package com.example;

import com.example.model.dto.CreateAuthorRequest;
import com.example.model.entities.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.service.AuthorService;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class L1002Application
        implements CommandLineRunner {


    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(L1002Application.class, args);
    }

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService service;

    @Override
    public void run(String... args) throws Exception {

//       service.updateBookPages(1L, 101);

//        service.updateBookAuthorName(1L, "updated_author_name_via_book_service");

        authorRepository.updateAuthorNameByIdNative("NEW_name_via_native_query", 13L);
    }

    void test() {
        authorService.findAllAuthors().forEach(System.out::println);

//        authorService.update(1L, "new_name");

//        authorRepository.updateAuthorNameById(1L, "new_name_via_query");

//        authorRepository.findAllAuthorsNative().forEach(System.out::println);


    }
}
