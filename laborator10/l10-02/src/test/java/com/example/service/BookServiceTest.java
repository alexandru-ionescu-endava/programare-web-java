package com.example.service;

import com.example.exceptions.AuthorException;
import com.example.exceptions.BookException;
import com.example.mapper.BookMapper;
import com.example.model.dto.BookRequest;
import com.example.model.dto.BookResponse;
import com.example.model.entities.Author;
import com.example.model.entities.Book;
import com.example.model.entities.BookDetail;
import com.example.model.entities.FormatType;
import com.example.repository.AuthorRepository;
import com.example.repository.BookDetailRepository;


import com.example.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookDetailRepository bookDetailRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void addBook_whenAuthorExists_savesBookAndReturnsResponse() {
        BookRequest request = BookRequest.builder()
                .title("Test Title")
                .pages(123)
                .publicationYear(2020)
                .formatType(FormatType.HARDCOVER)
                .authorId(1L)
                .build();

        Author author = new Author();
        author.setId(1L);
        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));

        BookDetail savedDetail = new BookDetail();
        savedDetail.setId(100L);
        savedDetail.setFormatType(request.formatType());
        savedDetail.setPublicationYear(request.publicationYear());
        when(bookDetailRepository.save(any(BookDetail.class)))
                .thenReturn(savedDetail);

        Book savedBook = new Book();
        savedBook.setId(100L);
        savedBook.setAuthor(author);
        savedBook.setTitle("Test Title");
        savedBook.setPages(123);
        savedBook.setBookDetail(savedDetail);

        when(bookRepository.save(any(Book.class)))
                .thenReturn(savedBook);

        BookResponse expected = BookResponse.builder()
                .id(100L)
                .title("Test Title")
                .pages(123)
                .publicationYear(2020)
                .formatType(FormatType.HARDCOVER)
                .authorName("Author Name")
                .build();

        when(bookMapper.fromEntity(savedBook))
                .thenReturn(expected);

        BookResponse actual = bookService.addBook(request);

        System.out.println(actual);
        System.out.println(expected);
        assertEquals(expected.getAuthorName(), actual.getAuthorName());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getPages(), actual.getPages());
        assertEquals(expected.getPublicationYear(), actual.getPublicationYear());

//       equals is implemented by @Data in BookResponse, comparing all fields
        assertEquals(expected, actual);
    }

    @Test
    void addBook_whenAuthorNotFound_throwsAuthorException() {
        BookRequest request = BookRequest.builder()
                .title("Test Title")
                .pages(123)
                .publicationYear(2020)
                .formatType(FormatType.HARDCOVER)
                .authorId(1L)
                .build();

        when(authorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(AuthorException.class, () -> bookService.addBook(request));

        verify(bookDetailRepository, never()).save(any());
        verify(bookRepository, never()).save(any());
    }

    @Test
    void updateBookPages_callsRepositoryUpdate() {
        Long bookId = 5L;
        int pages = 250;

        bookService.updateBookPages(bookId, pages);

        verify(bookRepository).updatePagesById(bookId, pages);
    }

    @Test
    void updateBookAuthorName_whenBookExists_updatesAndSaves() {
        Long bookId = 10L;
        String newName = "New Author";

        Author author = new Author();
        author.setName("Old Author");

        Book book = new Book();
        book.setId(bookId);
        book.setAuthor(author);

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.of(book));

        when(bookRepository.save(any(Book.class)))
                .thenReturn(book);

        bookService.updateBookAuthorName(bookId, newName);

        assertEquals(newName, author.getName());
        verify(bookRepository).save(book);
    }

    @Test
    void updateBookAuthorName_whenBookNotFound_throwsBookException() {
        Long bookId = 10L;
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.empty());

        assertThrows(BookException.class,
                () -> bookService.updateBookAuthorName(bookId, "New Author"));

        verify(bookRepository, never()).save(any());
    }

    @Test
    void findByBookFormat_returnsMappedList() {
        FormatType format = FormatType.EBOOK;

        Book book1 = new Book();
        book1.setId(1L);
        Book book2 = new Book();
        book2.setId(2L);

        when(bookRepository.findByBookDetailFormatType(format))
                .thenReturn(List.of(book1, book2));

        BookResponse resp1 = BookResponse.builder()
                .id(1L)
                .title("A")
                .pages(100)
                .publicationYear(2000)
                .formatType(format)
                .authorName("X")
                .build();

        BookResponse resp2 = BookResponse.builder()
                .id(2L)
                .title("B")
                .pages(200)
                .publicationYear(2001)
                .formatType(format)
                .authorName("Y")
                .build();

        when(bookMapper.fromEntity(book1)).thenReturn(resp1);
        when(bookMapper.fromEntity(book2)).thenReturn(resp2);

        List<BookResponse> result = bookService.findByBookFormat(format);

        assertEquals(2, result.size());
        assertTrue(result.contains(resp1));
        assertTrue(result.contains(resp2));
    }


    @Test
    void findById_whenBookExists_returnsMappedResponse() {
        Long bookId = 3L;
        Book book = new Book();
        book.setId(bookId);

        BookResponse response = BookResponse.builder()
                .id(bookId)
                .title("Title")
                .pages(100)
                .publicationYear(2020)
                .formatType(FormatType.EBOOK)
                .authorName("Author")
                .build();

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.of(book));

        when(bookMapper.fromEntity(book))
                .thenReturn(response);

        BookResponse result = bookService.findById(bookId);

        assertEquals(response, result);
    }


    @Test
    void findById_whenBookNotFound_throwsBookException() {
        Long bookId = 3L;
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.empty());

        assertThrows(BookException.class, () -> bookService.findById(bookId));
    }


}



