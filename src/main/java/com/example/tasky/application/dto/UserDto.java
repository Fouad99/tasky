package com.example.tasky.application.dto;

import com.example.tasky.domain.model.Task;

import java.util.List;
import java.util.UUID;

public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private List<Task> tasks;

    public UserDto(UUID id, String username, String email, List<Task> tasks) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
