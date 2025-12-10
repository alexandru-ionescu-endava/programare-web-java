package com.example.model;


import jakarta.persistence.*;

@Entity
@Table(name = "book_details")
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer publicationYear;

//    we use enumerated for storing enum values in the db
    @Enumerated(EnumType.STRING)
    private FormatType formatType;


//    we use mappedBy on the inverse side to specify the owner side attribute
    @OneToOne(mappedBy = "bookDetail")
    private Book book;


//    mapstruct

}
