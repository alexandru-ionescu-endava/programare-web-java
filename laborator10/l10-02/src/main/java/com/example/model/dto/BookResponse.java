package com.example.model.dto;

import com.example.model.entities.Book;
import com.example.model.entities.FormatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookResponse {

    private Long id;
    private String title;
    private Integer pages;

    private Integer publicationYear;

    private FormatType formatType;

    private String authorName;

//    private AuthorResponse author;


}
