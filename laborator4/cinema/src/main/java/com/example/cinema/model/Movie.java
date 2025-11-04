package com.example.cinema.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Movie {

    private Integer id;
    private String name;
    private Integer duration;
}
