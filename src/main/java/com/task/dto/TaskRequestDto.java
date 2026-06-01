package com.task.dto;

import com.task.util.TaskPriority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {

    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotBlank(message = "Description cannot be blank.")
    @Size(message = "Description cannot have more than 500 characters.", max = 500)
    private String description;

    @NotNull(message = "Priority must be LOW, MEDIUM, or HIGH.")
    private TaskPriority priority;

    @FutureOrPresent(message = "Due date must be in the present or future.")
    @NotNull(message = "Due date cannot be blank.")
    private LocalDateTime dueDate;
}
