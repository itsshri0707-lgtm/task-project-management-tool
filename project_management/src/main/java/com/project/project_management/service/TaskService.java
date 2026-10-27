package com.project.project_management.service;

import com.project.project_management.Storage.JsonFileStorage;
import com.project.project_management.model.Task;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final JsonFileStorage jsonFileStorage;

    public TaskService(JsonFileStorage jsonFileStorage) {
        this.jsonFileStorage = jsonFileStorage;
    }


    public List<Task> getAllTasks() throws IOException {
        return jsonFileStorage.readList("tasks.json", Task.class);
    }


    public Task getTaskById(String id) throws IOException {
        return getAllTasks()
                .stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public Task createTask(Task task) throws IOException {
        List<Task> tasks = getAllTasks();

        task.setId(UUID.randomUUID().toString());
        tasks.add(task);

        jsonFileStorage.writeList("tasks.json", tasks);

        return task;
    }


    public Task updateTask(String id, Task updatedTask)
            throws IOException {

        List<Task> tasks = getAllTasks();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                updatedTask.setId(id);
                tasks.set(i, updatedTask);

                jsonFileStorage.writeList("tasks.json", tasks);

                return updatedTask;
            }
        }

        return null;
    }


    public boolean deleteTask(String id) throws IOException {
        List<Task> tasks = getAllTasks();

        boolean removed = tasks.removeIf(
                task -> task.getId().equals(id));

        if (removed) {
            jsonFileStorage.writeList("tasks.json", tasks);
        }

        return removed;
    }
}
