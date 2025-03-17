package com.example.tasky.application.port.in.api;

import com.example.tasky.application.dto.TaskDto;

import java.util.UUID;

public interface TaskEndpointPort {
    TaskDto createTask(TaskDto taskDto);
    TaskDto updateTask(UUID taskId, TaskDto taskDto);
    TaskDto getTaskById(UUID taskId);
    void deleteTask(UUID taskId);
}

