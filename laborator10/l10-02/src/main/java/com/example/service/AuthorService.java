package com.example.service;

import com.example.exceptions.AuthorException;
import com.example.model.dto.AuthorResponse;
import com.example.model.dto.CreateAuthorRequest;
import com.example.mapper.AuthorMapper;
import com.example.model.entities.Author;
import com.example.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    public AuthorResponse findById(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorException("Author not found"));

        return authorMapper.fromEntity(author);
    }

    public List<AuthorResponse> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::fromEntity)
                .toList();
    }

    @Transactional
    public AuthorResponse addAuthor(CreateAuthorRequest createAuthorRequest) {

        Author author = authorMapper.fromCreateRequest(createAuthorRequest);
        authorRepository.save(author);

        return authorMapper.fromEntity(author);
    }


    @Transactional
    public AuthorResponse update(Long authorId, String newName) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorException("Author not found"));

        author.setName(newName);
        authorRepository.save(author);

        return authorMapper.fromEntity(author);
    }


    @Transactional
    public void add10Authors() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setName("Author " + i);

            if (i == 5)
                throw new RuntimeException();

            authorRepository.save(author);
        }
    }


}
