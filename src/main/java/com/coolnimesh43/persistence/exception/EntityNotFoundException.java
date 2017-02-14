package com.coolnimesh43.persistence.exception;

/**
 * Generic Exception for situations when Entity is not found. E.g: delete operation.
 * 
 * @author coolnimesh43
 *
 */
public class EntityNotFoundException extends RuntimeException {

    private String message;

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
