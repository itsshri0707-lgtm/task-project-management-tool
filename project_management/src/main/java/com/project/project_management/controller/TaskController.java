package com.project.project_management.controller;

import com.project.project_management.model.Task;
import com.project.project_management.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public List<Task> getAllTasks() throws IOException {
        return taskService.getAllTasks();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(
            @PathVariable String id) throws IOException {

        Task task = taskService.getTaskById(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody Task task) throws IOException {

        Task createdTask = taskService.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable String id,
            @RequestBody Task task) throws IOException {

        Task updatedTask = taskService.updateTask(id, task);

        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable String id) throws IOException {

        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}