package com.task.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String value) {
        super(String.format("User not found with id '%s'", value));
    }
}
