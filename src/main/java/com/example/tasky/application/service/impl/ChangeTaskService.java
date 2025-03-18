package com.example.tasky.application.service.impl;

import com.example.tasky.application.port.out.persistence.TaskPersistencePort;
import com.example.tasky.application.usecase.ChangeTaskUseCase;
import com.example.tasky.domain.exception.InvalidTaskStateException;
import com.example.tasky.domain.exception.TaskNotFoundException;
import com.example.tasky.domain.model.Task;
import com.example.tasky.domain.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChangeTaskService implements ChangeTaskUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ChangeTaskService.class);

    private final TaskPersistencePort taskPersistencePort;

    public ChangeTaskService(TaskPersistencePort taskPersistencePort){
        this.taskPersistencePort = taskPersistencePort;
    }

    @Override
    public Task updateTask(UUID taskId, Task task) {
        Optional<Task> existingTask = taskPersistencePort.findTaskById(taskId);

        if (existingTask.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }

        if (existingTask.get().getStatus() == TaskStatus.COMPLETED) {
            throw new InvalidTaskStateException("Task is already completed and cannot be updated.");
        }

        existingTask.get().updateStatus(task.getStatus());

        try {
            taskPersistencePort.saveTask(existingTask.get());
            logger.info("Task updated successfully: {}", task);
            return existingTask.get();
        } catch (Exception e) {
            logger.error("Error updating task: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating task.", e);
        }
    }
}
