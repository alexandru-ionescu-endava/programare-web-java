package com.example.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;




    /*  bidirectional many-to-many relationship
	   direct side of the relationship - @JoinTable
	   if @JoinTable is not used, Hibernate will generate the join table with the default settings
	*/

//    inverse side of the relationship - mappedBy
    @ManyToMany(mappedBy = "categories")
    private List<Book> books;


}
