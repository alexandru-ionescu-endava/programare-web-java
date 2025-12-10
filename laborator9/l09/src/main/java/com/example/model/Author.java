package com.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    id ul este disponibil doar dupa insert


    /*   inverse side of the relationship
		 this is a bidirectional one-to-many relationship
		 the optimal way to implement a one-to-many relationship:

		 1. unidirectional relationship with @ManyToOne on one entity
		 and nothing on the other entity

		 2. bidirectional relationship with @ManyToOne on one entity
		  and @OneToMany on the other entity
	 */


//    suporta batch insert si bulk operations
//    bun pentru aplicatii cu trafic mare
//    id ul este disponibil inainte de insert

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;


//    identity insert into book (title) values ('title') returning id;
//    sequence for batch inserts: nextVal() => 1 insert into book (id, title) values (1, 'title');




//    uuid este util in sisteme distribuite unde unicitatea globala este necesara
//    microservicii

//    @Id
//    @GeneratedValue
//    @UuidGenerator
//    private UUID id;
//    example of uuid: 550e8400-e29b-41d4-a716-446655440000

    private String name;





//    Set<Book> books for better performance with large collections
//    set contains is O(1) while list contains is O(n)
//    choosing between List and Set depends on the use case and performance requirements
//
    //   use mappedBy to specify the attribute in Book that owns the relationship
//    @OneToMany(mappedBy = "author")
//    private Set<Book> books;

    @OneToMany(mappedBy = "author")
    private List<Book> books;



}
