package com.project.project_management.model;

import java.time.LocalDate;

public class Task {
    private String id;
    private String title;
    private String description;
    private String projectId;
    private String assignedMemberId;

    private Priority priority;
    private TaskStatus status;
    private LocalDate deadline;

    public Task() {
    }

    public Task(String id, String title, String description,
                String projectId, String assignedMemberId,
                Priority priority, TaskStatus status,
                LocalDate deadline) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.projectId = projectId;
        this.assignedMemberId = assignedMemberId;
        this.priority = priority;
        this.status = status;
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAssignedMemberId() {
        return assignedMemberId;
    }

    public void setAssignedMemberId(String assignedMemberId) {
        this.assignedMemberId = assignedMemberId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

}
