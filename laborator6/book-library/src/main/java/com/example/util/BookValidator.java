package com.example.util;

import com.example.exception.BookBatchValidationException;
import com.example.model.BookUpdateDTO;
import com.example.model.BookValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class BookValidator {

    private final Validator validator;

    public void validateBooks(List<BookUpdateDTO> requestList) {
        List<BookValidationError> errorList = new ArrayList<>();

        for (BookUpdateDTO dto : requestList) {
            Set <ConstraintViolation<BookUpdateDTO> > violations = validator.validate(dto);
            for (ConstraintViolation<BookUpdateDTO> violation : violations) {
                String field = violation.getPropertyPath().toString();
                String message = violation.getMessage();

                errorList.add(new BookValidationError(dto.id(), field, message));
            }
        }

        if (!errorList.isEmpty())
            throw new BookBatchValidationException(errorList);
    }
}
