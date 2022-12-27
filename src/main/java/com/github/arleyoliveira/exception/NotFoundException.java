package com.github.arleyoliveira.exception;

abstract public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
