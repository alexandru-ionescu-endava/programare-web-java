package com.example.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer publicationYear;

    @Enumerated(EnumType.STRING)
    private FormatType formatType;

}

