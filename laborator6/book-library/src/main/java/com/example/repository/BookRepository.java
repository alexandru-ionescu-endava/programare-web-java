package com.example.repository;

import com.example.model.Book;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.Objects.isNull;

@Component
public class BookRepository {

    private Map<Integer, Book> storage =
            new HashMap<>();

    public BookRepository() {

        saveOrUpdate(Book.builder()
                .title("Fratii Karamazov")
                .author("Dostoievski")
                .pages(790)
                .build());

        saveOrUpdate(Book.builder()
                .title("Crima si pedeapsa")
                .author("Dostoievski")
                .pages(900)
                .build());

        saveOrUpdate(Book.builder()
                .title("Anna Karenina")
                .author("Tolstoi")
                .pages(1000)
                .build());
    }

    public Book saveOrUpdate(Book book) {
        if (isNull(book.getId())) {
            Integer id = new Random().nextInt(1, 50);
            book.setId(id);
        }
        storage.put(book.getId(), book);

        return book;
    }

    public Collection<Book> findAll(Map<String, String> filters) {
        return storage.values().stream()
                .filter(book -> {
                    if (isNull(filters)) return true;

                    if (filters.containsKey("title")
                            && !book.getTitle().equals(filters.get("title")))
                        return false;

                    if (filters.containsKey("author")
                            && !book.getAuthor().equals(filters.get("author")))
                        return false;

                    if (filters.containsKey("minPages")) {
                        Integer minPages = Integer.parseInt(filters.get("minPages"));
                        return book.getPages() >= minPages;
                    }
                    return true;
                })
                .toList();
    }

    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }
}
