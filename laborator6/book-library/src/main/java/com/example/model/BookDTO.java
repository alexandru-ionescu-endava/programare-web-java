package com.example.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record BookDTO(
        @NotNull(message = "book title cannot be null")
        @NotBlank(message = "book title cannot be blank")
        String title,

        @Length(min = 1, max = 30, message = "Author name should be between 1 and 30")
        String author,

        @Min(value = 1, message = "Pages number should be greater than 1")
        Integer pages) {
}
