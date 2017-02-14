package com.coolnimesh43.persistence.exception;

public class EntityNotFoundException extends RuntimeException {

    private String message;

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
