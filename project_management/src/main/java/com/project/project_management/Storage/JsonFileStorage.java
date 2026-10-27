package com.project.project_management.Storage;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JsonFileStorage {

    private final ObjectMapper objectMapper;

    private final Path dataDirectory =
            Paths.get("data").toAbsolutePath().normalize();

    private final Set<String> allowedFiles = Set.of(
            "projects.json",
            "tasks.json",
            "team-members.json"
    );

    public JsonFileStorage(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void initializeFiles() {
        try {
            Files.createDirectories(dataDirectory);

            for (String fileName : allowedFiles) {
                Path filePath = getFilePath(fileName);

                if (Files.notExists(filePath)) {
                    Files.writeString(filePath, "[]");
                }
            }

            System.out.println("Local JSON storage initialized.");

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to initialize local JSON storage.", e);
        }
    }

    public <T> List<T> readList(
            String fileName, Class<T> itemClass) throws IOException {

        Path filePath = getFilePath(fileName);

        if (Files.notExists(filePath)) {
            return new ArrayList<>();
        }

        JavaType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, itemClass);

        return objectMapper.readValue(filePath.toFile(), listType);
    }

    public <T> void writeList(
            String fileName, List<T> data) throws IOException {

        Path filePath = getFilePath(fileName);

        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(filePath.toFile(), data);
    }

    private Path getFilePath(String fileName) {

        if (!allowedFiles.contains(fileName)) {
            throw new IllegalArgumentException(
                    "Invalid storage file: " + fileName);
        }

        return dataDirectory.resolve(fileName);
    }
}

