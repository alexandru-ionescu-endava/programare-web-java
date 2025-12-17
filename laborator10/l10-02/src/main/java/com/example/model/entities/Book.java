package com.example.model.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "books")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title", unique = true, nullable = false, updatable = false)
    private String title;

    @Column(name = "pages")
    private Integer pages;


    @OneToOne
    @JoinColumn(name = "detail_id")
    private BookDetail bookDetail;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
