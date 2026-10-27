package com.project.project_management.controller;

import com.project.project_management.model.Task;
import com.project.project_management.service.ProjectProgressService;
import com.project.project_management.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progress")
public class ProjectProgressController {

    private final ProjectProgressService progressService;
    private final TaskService taskService;

    public ProjectProgressController(
            ProjectProgressService progressService,
            TaskService taskService) {
        this.progressService = progressService;
        this.taskService = taskService;
    }

    @GetMapping
    public Map<String, Object> getProgress() throws IOException {
        List<Task> tasks = taskService.getAllTasks();

        double progress = progressService.calculateProgress(tasks);

        return Map.of(
                "totalTasks", tasks.size(),
                "completedTasks", tasks.stream()
                        .filter(task -> task.getStatus()
                                == com.project.project_management.model.TaskStatus.COMPLETED)
                        .count(),
                "progressPercentage", progress
        );
    }

    @GetMapping("/overdue")
    public List<Task> getOverdueTasks() throws IOException {
        return progressService.getOverdueTasks(
                taskService.getAllTasks());
    }

    @GetMapping("/projects")
    public Map<String, Double> getProjectProgress() throws IOException {
        List<Task> tasks = taskService.getAllTasks();

        return tasks.stream()
                .collect(Collectors.groupingBy(
                        task -> java.util.Objects.requireNonNull(task).getProjectId(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                progressService::calculateProgress
                        )
                ));
    }
}