package com.example.service;

import com.example.model.dto.BookResponse;
import com.example.exceptions.BookException;
import com.example.mapper.BookMapper;
import com.example.model.entities.Author;
import com.example.model.entities.Book;
import com.example.model.dto.BookRequest;
import com.example.model.entities.BookDetail;
import com.example.model.entities.FormatType;
import com.example.repository.AuthorRepository;
import com.example.repository.BookDetailRepository;
import com.example.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;


    @Transactional
    public BookResponse addBook(BookRequest bookRequest) {

        Author author = authorRepository.findById(bookRequest.authorId())
                .orElseThrow(() -> new BookException("Author not found"));

        BookDetail bookDetail = new BookDetail();
        bookDetail.setPublicationYear(bookRequest.publicationYear());
        bookDetail.setFormatType(bookRequest.formatType());
        BookDetail savedBD = bookDetailRepository.save(bookDetail);

        Book book = new Book();
        book.setTitle(bookRequest.title());
        book.setPages(bookRequest.pages());
        book.setBookDetail(savedBD);

        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        log.info("book {} was successfully saved in db.", savedBook);

        return bookMapper.fromEntity(savedBook);
    }


    @Transactional
    public void updateBookPages(Long bookId, int pages) {
        bookRepository.updatePagesById(bookId, pages);
    }


    @Transactional
    public void updateBookAuthorName(Long bookId, String newName) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found"));

        book.getAuthor().setName(newName);

        bookRepository.save(book);
    }


    public List<BookResponse> findByBookFormat(FormatType formatType) {
        List<Book> books = bookRepository.findByBookDetailFormatType(formatType);

        return books.stream()
                .map(bookMapper::fromEntity).toList();
    }


    @Transactional
    public void add10() {
        for (int i = 0; i < 10; i++) {
            addBook(BookRequest.builder()
                    .title("book_title_" + i)
                    .pages(i * 10 + 1)

                    .publicationYear(2000 + i)
                    .formatType(i % 2 == 0 ? FormatType.HARDCOVER : FormatType.EBOOK)

                    .authorId(i % 2 == 0 ? 12L : 13L)

                    .build()
            );
        }
    }
}
