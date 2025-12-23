package com.example.service;

import com.example.exceptions.AuthorException;
import com.example.mapper.AuthorMapper;
import com.example.model.dto.AuthorResponse;
import com.example.model.dto.CreateAuthorRequest;
import com.example.model.entities.Author;
import com.example.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void whenAuthorExists_findById_returnsAuthorResponse() {
//        Arrange
        Author entity = new Author(1L, "Author Name");

        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        when(authorMapper.fromEntity(entity))
                .thenReturn(new AuthorResponse(1L, "Author Name"));

//        Act
        AuthorResponse response = authorService.findById(1L);

//        Assert
        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
        verify(authorRepository).findById(1L);
    }

    @Test
    void whenAuthorDoesntExist_findById_throwsException() {
//        Arrange
        when(authorRepository.findById(99L))
                .thenReturn(Optional.empty());

//        Act
        AuthorException exception = assertThrows(AuthorException.class,
                () -> authorService.findById(99L));

//        Assert
        assertEquals("Author not found", exception.getMessage());
        verify(authorRepository).findById(99L);
    }

    @Test
    void whenAuthorsExist_returnsListOfAuthorResponses() {
//        Arrange
        Author author1 = new Author(1L, "Author One");
        Author author2 = new Author(2L, "Author Two");

        when(authorRepository.findAll())
                .thenReturn(List.of(author1, author2));

        when(authorMapper.fromEntity(author1))
                .thenReturn(new AuthorResponse(1L, "Author One"));
        when(authorMapper.fromEntity(author2))
                .thenReturn(new AuthorResponse(2L, "Author Two"));

//        Act
        List<AuthorResponse> responses = authorService.findAllAuthors();


//        Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("Author One", responses.get(0).getName());
        assertEquals("Author Two", responses.get(1).getName());

        verify(authorRepository).findAll();
    }

    @Test
    void whenAuthorExists_updateName() {
//        Arrange
        Author entity = new Author(1L, "Old Name");

        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        when(authorMapper.fromEntity(entity))
                .thenReturn(new AuthorResponse(1L, "New Name"));

//        Act
        AuthorResponse response = authorService.update(1L, "New Name");

//        Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("New Name", response.getName());
        verify(authorRepository).findById(1L);
        verify(authorRepository).save(entity);
    }


    @Test
    void whenCreateAuthor_addsAuthor() {
        // Arrange
        CreateAuthorRequest createRequest = new CreateAuthorRequest("New Author");
        Author entity = new Author();
        entity.setName("New Author");

        when(authorMapper.fromCreateRequest(createRequest))
                .thenReturn(entity);

        when(authorRepository.save(entity))
                .thenReturn(entity);

        when(authorMapper.fromEntity(entity))
                .thenReturn(new AuthorResponse(1L, "New Author"));

        // Act
        AuthorResponse response = authorService.addAuthor(createRequest);


        // Assert
        assertNotNull(response);
        assertEquals("New Author", response.getName());
        verify(authorMapper).fromCreateRequest(createRequest);
        verify(authorRepository).save(entity);
        verify(authorMapper).fromEntity(entity);
    }


    @Test
    void whenCreateAuthorRequestIsNull_addAuthor_throwsException() {
//         Arrange
        CreateAuthorRequest createRequest = null;
        when(authorMapper.fromCreateRequest(createRequest))
                .thenThrow(NullPointerException.class);

//         Act
        assertThrows(NullPointerException.class, () -> authorService.addAuthor(createRequest));

//        Assert
        verify(authorRepository, never()).save(any(Author.class));
    }


}
