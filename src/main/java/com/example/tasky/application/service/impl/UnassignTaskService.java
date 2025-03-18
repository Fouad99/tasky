package com.example.tasky.application.service.impl;

import com.example.tasky.application.port.out.persistence.TaskPersistencePort;
import com.example.tasky.application.usecase.UnassignTaskUseCase;
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
public class UnassignTaskService implements UnassignTaskUseCase {

    private static final Logger logger = LoggerFactory.getLogger(UnassignTaskService.class);
    private final TaskPersistencePort taskPersistencePort;

    public UnassignTaskService(TaskPersistencePort taskPersistencePort) {
        this.taskPersistencePort = taskPersistencePort;
    }

    @Override
    public void unassignTask(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }

        Optional<Task> task = taskPersistencePort.findTaskById(taskId);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }

        if (task.get().getStatus().equals(TaskStatus.COMPLETED)){
            throw new InvalidTaskStateException("Cannot unassign a task that is already completed.");
        }

        task.get().setUserId(null);

        try {
            taskPersistencePort.saveTask(task.get());
            logger.info("Task with ID {} unassigned", taskId);
        } catch (Exception e) {
            logger.error("Error unassigning task with ID {}: {}", taskId, e.getMessage(), e);
            throw new RuntimeException("Error unassigning task.", e);
        }
    }

}
