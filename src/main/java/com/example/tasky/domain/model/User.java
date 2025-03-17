package com.example.tasky.domain.model;

import java.util.*;

public class User {
    private final UUID id;
    private String username;
    private String email;
    private List<Task> tasks;

    public User(String username, String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.tasks = new ArrayList<>();
    }

    public User(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tasks = new ArrayList<>();
    }

    public User(UUID id, String username, String email, List<Task> tasks) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tasks = tasks;
    }

    public UUID getId(){
        return id;
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

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks); // Prevent external modification
    }
}
