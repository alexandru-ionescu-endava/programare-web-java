package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Book {

    private Integer id;
    private String title;
    private String author;
    private Integer pages;
}
