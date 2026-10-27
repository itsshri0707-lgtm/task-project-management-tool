package com.project.project_management.controller;

import com.project.project_management.model.Project;
import com.project.project_management.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() throws IOException {
        return projectService.getAllProjects();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable String id) throws IOException {

        Project project = projectService.getProjectById(id);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(project);
    }


    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project project) throws IOException {

        Project createdProject = projectService.createProject(project);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdProject);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable String id,
            @RequestBody Project project) throws IOException {

        Project updatedProject =
                projectService.updateProject(id, project);

        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProject);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable String id) throws IOException {

        boolean deleted = projectService.deleteProject(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
