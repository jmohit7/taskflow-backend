package com.task.service;

import com.task.dto.TaskRequestDto;
import com.task.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto fetchTask(String id);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto updateTask(String id, TaskRequestDto taskRequestDto);
    String deleteTask(String id);
}
