package com.project.project_management.service;

import com.project.project_management.Storage.JsonFileStorage;
import com.project.project_management.model.Project;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final JsonFileStorage jsonFileStorage;

    public ProjectService(JsonFileStorage jsonFileStorage) {
        this.jsonFileStorage = jsonFileStorage;
    }


    public List<Project> getAllProjects() throws IOException {
        return jsonFileStorage.readList(
                "projects.json", Project.class);
    }


    public Project getProjectById(String id) throws IOException {
        return getAllProjects()
                .stream()
                .filter(project -> project.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public Project createProject(Project project) throws IOException {
        List<Project> projects = getAllProjects();

        String newId = UUID.randomUUID().toString();
        project.setId(newId);

        projects.add(project);

        jsonFileStorage.writeList("projects.json", projects);

        return project;
    }


    public Project updateProject(
            String id, Project updatedProject) throws IOException {

        List<Project> projects = getAllProjects();

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId().equals(id)) {
                updatedProject.setId(id);
                projects.set(i, updatedProject);

                jsonFileStorage.writeList("projects.json", projects);

                return updatedProject;
            }
        }

        return null;
    }


    public boolean deleteProject(String id) throws IOException {
        List<Project> projects = getAllProjects();

        boolean removed = projects.removeIf(
                project -> project.getId().equals(id));

        if (removed) {
            jsonFileStorage.writeList("projects.json", projects);
        }

        return removed;
    }
}