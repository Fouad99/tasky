package com.example.tasky.application.dto;

import com.example.tasky.domain.model.TaskStatus;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private ZonedDateTime createTime;
    private ZonedDateTime updateTime;
    private UUID userId;

    public TaskDto(UUID id, String title, String description, TaskStatus status, ZonedDateTime createTime, ZonedDateTime updateTime, UUID userId) {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
