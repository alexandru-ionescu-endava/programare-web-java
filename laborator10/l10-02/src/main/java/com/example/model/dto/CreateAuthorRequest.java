package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateAuthorRequest(
        @NotNull(message = "author name annot be null")
        @NotBlank(message = "author name cannot be blank")
        String name
) {
}
