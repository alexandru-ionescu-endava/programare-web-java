package com.example.model;

import jakarta.persistence.*;

import java.util.List;


@Entity // entity means that this class is mapped to a db table
@Table(name = "books") //@Table means that we can customize the table mapping
public class Book {


    @Id // id means that this attribute is mapped to the PK of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // generated value means that the value will be generated auto_increment by the db
    private Long id;

    // column means that we can customize the column mapping
    @Column(name = "book_title", unique = true, nullable = false, updatable = false)
    private String title;

    @Column(name = "pages")
    private Integer pages;

    public Book() {
        // every entity should have the default constructor
    }



//   we use @JoinColumn on owner side to specify the FK column
    @OneToOne
    @JoinColumn(name = "detail_id")
    private BookDetail bookDetail;


    /* direct side of the relationship -> FK
           this is a bidirectional one-to-many relationship
           the optimal way to implement a one-to-many relationship is:
           1. unidirectional relationship with @ManyToOne on one entity and nothing on the other entity
           2. bidirectional relationship with @ManyToOne on one entity and @OneToMany on the other entity
     */

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;






//    	 bidirectional many-to-many relationship
//	   direct side of the relationship - @JoinTable

    @ManyToMany
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

}
