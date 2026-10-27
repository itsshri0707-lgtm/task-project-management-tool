package com.project.project_management.model;

import java.time.LocalDate;

public class Project {
    private String id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate deadline;

    public Project() {
    }

    public Project(String id, String name, String description,
                   LocalDate startDate, LocalDate deadline) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

}
