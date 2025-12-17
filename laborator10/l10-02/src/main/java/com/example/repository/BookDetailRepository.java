package com.example.repository;

import com.example.model.entities.BookDetail;
import com.example.model.entities.FormatType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository
        extends JpaRepository<BookDetail, Long> {


    @Query(value = """
                update BookDetail bd set bd.formatType = :formatType where bd.id = :bookDetailId
            """)
    @Modifying
    @Transactional
    void updateFormatTypeById(FormatType formatType, Long bookDetailId);

}
