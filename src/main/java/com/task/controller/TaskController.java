package com.task.controller;

import com.task.dto.TaskRequestDto;
import com.task.dto.TaskResponseDto;
import com.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        return new ResponseEntity<>(taskService.createTask(taskRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable String id) {
        return new ResponseEntity<>(taskService.fetchTask(id), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable String id, @RequestBody TaskRequestDto taskRequestDto) {
        return new ResponseEntity<>(taskService.updateTask(id, taskRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.OK);
    }
}
