package com.example.mapper;

import com.example.model.dto.AuthorResponse;
import com.example.model.dto.CreateAuthorRequest;
import com.example.model.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorResponse fromEntity(Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        return response;
    }

    public Author fromCreateRequest(CreateAuthorRequest request) {
        Author author = new Author();
        author.setName(request.name());
        return author;
    }
}
