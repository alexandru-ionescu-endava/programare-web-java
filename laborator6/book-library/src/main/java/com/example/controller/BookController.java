package com.example.controller;


import com.example.exception.UserNotAllowedException;
import com.example.model.Book;
import com.example.model.BookDTO;
import com.example.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //    deserialize JSON to java object (BookDTO)
//    serialize java object to JSON
    @PostMapping
    public ResponseEntity<?> add(@RequestBody BookDTO request,
                                 @RequestHeader("Username") String username) {

        if (username.equals("admin")) {
            Book book = bookService.add(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(book);
        }

        throw new UserNotAllowedException();
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(required = false) Map<String, String> filters) {
        var books = bookService.findAll(filters);

        return books.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("")
                : ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping
    public ResponseEntity<String> hello(@RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(auth);
    }

    //    put update whole resource
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @Valid @RequestBody BookDTO request) {

        Book updated = bookService.replaceBook(id, request);

        return ResponseEntity.ok(updated);
    }

    //    update partial
    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable Integer id,
                                   @RequestBody BookDTO request) {

        Book updated = bookService.patchBook(id, request);

        return ResponseEntity.ok(updated);
    }


//    TODO
//    @PutMapping
//    public ResponseEntity<?> batchUpdate(@Valid @RequestBody List<BookDTO> requestList) {
//
//        for (BookDTO bookDTO : requestList) {
//            bookService.replaceBook(bookDTO.id(), bookDTO);
//        }
//
//        return ResponseEntity.ok(requestList);
//    }


}
