package com.example.repository;

import com.example.model.entities.Book;
import com.example.model.entities.FormatType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository
        extends JpaRepository<Book, Long> {

//    query method only for simple queries, select, count

    //    query from method name
    long countByBookDetailFormatType(FormatType formatType);

    //    query from method name
    long countByAuthorId(Long authorId);





//    JPQL query to find books by format type
    @Query("""
                    select b from Book b
                    where b.bookDetail.formatType = :formatType
            """)
    List<Book> findByBookDetailFormatType(FormatType formatType);




//    JPQL update query to update pages by book id
//    use Modifying and Transactional annotations on update queries
    @Transactional
    @Modifying
    @Query("""
                    update Book b set b.pages = :pages where b.id = :bookId
            """)
    void updatePagesById(@Param("bookId") Long bookId, @Param("pages") int pages);



    @Query("""
                                select b from Book b
                                join fetch b.author
                                join fetch b.bookDetail
            """)
    List<Book> findAllWithJoinFetch();




    @NativeQuery(value = "select * from books")
    List<Book> findAllBooks();



}
