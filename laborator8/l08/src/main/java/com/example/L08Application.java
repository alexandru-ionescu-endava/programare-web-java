package com.example;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class L08Application
        implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(L08Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(dataSource.getConnection());
        System.out.println();

        bookRepository.findAllExtended().stream()
                .forEach(System.out::println);

        for (int i = 0; i < 10; i++) {
            bookRepository.insertBook(Book.builder()
                    .title("test_title_" + i)
                    .author("test_author" + i)
                    .pages(10 * i + 1)
                    .build());
        }

//        SQL Injection example will delete all records in 'book' table
//        bookRepository.badExample();

        bookRepository.findAll()
                .forEach(System.out::println);

        System.out.println(bookRepository.findById(8));
        bookRepository.groupByAuthor().stream().forEach(System.out::println);

        bookRepository.findAllByPages(9).stream()
                .forEach(System.out::println);

    }
}
