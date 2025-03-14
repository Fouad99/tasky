package com.example.tasky.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private final UUID id;
    private String username;
    private String email;
    private Set<Task> tasks;

    public User(String username, String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.tasks = new HashSet<>();
    }

    public User(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tasks = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addTask(Task task) {
        if (task != null){
            task.setUserId(this.id);
            this.tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        if (task != null){
            task.setUserId(null);
            this.tasks.remove(task);
        }
    }

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks); // Prevent external modification
    }
}
