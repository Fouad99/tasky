package com.example.tasky.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;

    public UserEntity(UUID id, String username, String email, List<TaskEntity> tasks) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tasks = tasks;
    }

    public UserEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public void addTask(TaskEntity task) {
        tasks.add(task);
        task.setUser(this);
    }

    public void removeTask(TaskEntity task) {
        tasks.remove(task);
        task.setUser(null);
    }
}
