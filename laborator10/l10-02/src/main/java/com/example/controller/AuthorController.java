package com.example.controller;


import com.example.model.dto.CreateAuthorRequest;
import com.example.model.dto.AuthorResponse;
import com.example.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody @Valid CreateAuthorRequest request) {
        log.info("Create Author Request: {}", request);
        AuthorResponse response = authorService.addAuthor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        log.info("Get All Authors Request");
        List<AuthorResponse> response = authorService.findAllAuthors();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable("id") Long id) {
        log.info("Get Author for id: {}", id);
        AuthorResponse response = authorService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable("id") Long id,
                                                 @NotNull @RequestParam("name") String newName) {

        log.info("Update Author Name Request for id: {}, new name: {}", id, newName);
        AuthorResponse response = authorService.update(id, newName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}

