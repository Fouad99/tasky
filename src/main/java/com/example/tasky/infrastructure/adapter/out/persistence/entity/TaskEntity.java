package com.example.tasky.infrastructure.adapter.out.persistence.entity;

import com.example.tasky.domain.model.TaskStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    private UUID id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private TaskStatus status;

    @Column(nullable = false, name = "create_time")
    private ZonedDateTime createTime;

    @Column(nullable = false, name = "update_time")
    private ZonedDateTime updateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;


    // Default constructor for JPA
    protected TaskEntity() {}

    public TaskEntity(UUID id, String title, String description, TaskStatus status, ZonedDateTime createTime, ZonedDateTime updateTime, UserEntity user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
    }

    public TaskEntity(UUID id, String title, String description, TaskStatus status, ZonedDateTime createTime, ZonedDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Getters and setters

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


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
