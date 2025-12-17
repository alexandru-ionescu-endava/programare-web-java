package com.example.model.dto;


import com.example.model.entities.FormatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookRequest(

        @NotNull(message = "book title cannot be null")
        @NotBlank(message = "book title cannot be blank")
        String title,

        @Min(value = 1, message = "Pages number should be greater than 1")
        Integer pages,

        Integer publicationYear,
        FormatType formatType,

        @NotNull Long authorId) {

}

