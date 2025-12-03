package com.example.exception;

import com.example.model.BookValidationError;
import lombok.Getter;

import java.util.List;

@Getter
public class BookBatchValidationException
        extends RuntimeException {

    private final List<BookValidationError> errorList;

    public BookBatchValidationException(List<BookValidationError> errorList) {
        super("Batch validation failed with " + errorList.size() + " errors.");
        this.errorList = errorList;
    }

}
