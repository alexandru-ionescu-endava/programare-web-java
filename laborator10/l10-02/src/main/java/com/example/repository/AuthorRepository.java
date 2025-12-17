package com.example.repository;

import com.example.model.entities.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository
        extends JpaRepository<Author, Long> {


    @Query(value = """
                update Author a set a.name = :newName where a.id = :authorId
            """)
    @Modifying
    @Transactional
    void updateAuthorNameById(Long authorId, String newName);


    @Query(
            value = """
                    update authors
                    set name = :newName
                    where id  = :authorId
                    """,
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void updateAuthorNameByIdNative(String newName, Long authorId);


    @NativeQuery("select * from authors")
    List<Author> findAllAuthorsNative();
}
