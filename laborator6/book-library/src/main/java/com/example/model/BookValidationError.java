package com.example.model;

public record BookValidationError(
        Integer id,
        String field,
        String message
) {
}
