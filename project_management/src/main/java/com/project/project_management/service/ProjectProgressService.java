package com.project.project_management.service;

import com.project.project_management.model.Task;
import com.project.project_management.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectProgressService {

    public boolean isTaskOverdue(Task task) {
        return task.getDeadline() != null
                && task.getDeadline().isBefore(LocalDate.now())
                && task.getStatus() != TaskStatus.COMPLETED;
    }


    public double calculateProgress(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return 0.0;
        }

        long completedTasks = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                .count();

        return (completedTasks * 100.0) / tasks.size();
    }

    public List<Task> getOverdueTasks(List<Task> tasks) {
        return tasks.stream()
                .filter(this::isTaskOverdue)
                .toList();
    }
}