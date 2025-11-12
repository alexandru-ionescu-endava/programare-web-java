package com.example.model;

public record ProductUpdateRequest(
        String title,
        Double price
) {
}
