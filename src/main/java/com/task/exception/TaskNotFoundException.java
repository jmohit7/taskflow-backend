package com.task.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String value) {
        super(String.format("Task not found with %s", value));
    }
}
