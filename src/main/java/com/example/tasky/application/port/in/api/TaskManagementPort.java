package com.example.tasky.application.port.in.api;

import com.example.tasky.domain.model.Task;
import com.example.tasky.domain.model.TaskStatus;

import java.util.UUID;

public interface TaskManagementPort {
    Task createTask(String title, String description, UUID userId);
    Task updateTask(UUID taskId, TaskStatus status);
    Task getTaskById(UUID taskId);
    void deleteTask(UUID taskId);
}
