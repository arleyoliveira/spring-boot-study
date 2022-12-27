package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.exception.BOException;
import com.github.arleyoliveira.exception.NotFoundException;
import com.github.arleyoliveira.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBOException(BOException exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleNotFoundException(NotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }
}
