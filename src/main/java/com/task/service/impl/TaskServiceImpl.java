package com.task.service.impl;

import com.task.dto.TaskRequestDto;
import com.task.dto.TaskResponseDto;
import com.task.entity.Task;
import com.task.exception.TaskNotFoundException;
import com.task.repository.TaskRepository;
import com.task.service.TaskService;
import com.task.util.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = mapToTask(taskRequestDto);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        task.setId("TASK-" + dateFormat.format(LocalDateTime.now()));
        Task savedTask = taskRepository.save(task);

        return mapToTaskResponseDto(savedTask);
    }

    @Override
    public TaskResponseDto fetchTask(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return mapToTaskResponseDto(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToTaskResponseDto)
                .toList();
    }

    @Override
    public TaskResponseDto updateTask(String id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setDescription(taskRequestDto.getDescription());
        task.setTitle(taskRequestDto.getTitle());
        task.setDueDate(taskRequestDto.getDueDate());
        task.setPriority(taskRequestDto.getPriority());
        Task updatedTask = taskRepository.save(task);
        return mapToTaskResponseDto(updatedTask);
    }

    @Override
    public String deleteTask(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
        return String.format("Task with id '%s' deleted successfully!", id);
    }

    private TaskResponseDto mapToTaskResponseDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setStatus(task.getStatus());
        taskResponseDto.setCreatedAt(task.getCreatedAt());
        taskResponseDto.setUpdatedAt(task.getUpdatedAt());
        taskResponseDto.setDueDate(task.getDueDate());
        taskResponseDto.setPriority(task.getPriority());

        return taskResponseDto;
    }

    private Task mapToTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setPriority(taskRequestDto.getPriority());
        task.setDueDate(taskRequestDto.getDueDate());
        task.setStatus(TaskStatus.PENDING);

        return task;
    }
}
