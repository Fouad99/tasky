package com.example.tasky.domain.model;

import com.example.tasky.domain.exception.InvalidTaskStateException;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Task {
    private final UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private final ZonedDateTime createTime;
    private ZonedDateTime updateTime;
    private UUID userId;

    public Task(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
        this.createTime = ZonedDateTime.now();
        this.updateTime = ZonedDateTime.now();
    }

    public Task(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
        this.createTime = ZonedDateTime.now();
        this.updateTime = ZonedDateTime.now();
    }

    public Task(UUID id, String title, String description, TaskStatus status, ZonedDateTime createTime, ZonedDateTime updateTime, UUID userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void updateTitle(String title){
        this.title = title;
        this.updateTime = ZonedDateTime.now();
    }

    public void updateDescription(String description){
        this.description = description;
        this.updateTime = ZonedDateTime.now();
    }

    public void updateStatus(TaskStatus status){
        this.status = status;
        this.updateTime = ZonedDateTime.now();
    }
}
