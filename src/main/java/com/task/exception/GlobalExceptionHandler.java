package com.task.exception;

import com.task.util.TaskPriority;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> getMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> getHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, String> errors = new HashMap<>();
        String priority = exception.getMessage();

        if (priority.contains("TaskPriority")) {
            String priorityValues = Arrays.stream(TaskPriority.values())
                            .map(Enum::name)
                            .collect(Collectors.joining(", "));
            errors.put("priority", "Priority must be one of: " + priorityValues);
        } else {
            errors.put("priority", "Invalid priority format.");
        }

        return errors;
    }
}
