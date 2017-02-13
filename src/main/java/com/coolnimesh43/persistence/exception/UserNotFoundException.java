package com.coolnimesh43.persistence.exception;

public class UserNotFoundException extends Exception {

    private String message;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
