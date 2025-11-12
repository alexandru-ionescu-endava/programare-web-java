package com.example.exception;

public class DuplicateProductException
        extends RuntimeException {

    public DuplicateProductException() {
        super("This product is already exists");
    }
}
