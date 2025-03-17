package com.example.tasky.application.service.impl;

import com.example.tasky.application.port.in.api.TaskAssignmentEndpointPort;
import com.example.tasky.application.port.out.persistence.TaskPersistencePort;
import com.example.tasky.application.port.out.persistence.UserPersistencePort;
import com.example.tasky.domain.exception.InvalidTaskStateException;
import com.example.tasky.domain.exception.TaskNotFoundException;
import com.example.tasky.domain.exception.UserNotFoundException;
import com.example.tasky.domain.model.Task;
import com.example.tasky.domain.model.TaskStatus;
import com.example.tasky.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskAssignmentService implements TaskAssignmentEndpointPort {

    private static final Logger logger = LoggerFactory.getLogger(TaskAssignmentService.class);
    private final TaskPersistencePort taskPersistencePort;
    private final UserPersistencePort userPersistencePort;

    public TaskAssignmentService(TaskPersistencePort taskPersistencePort, UserPersistencePort userPersistencePort){
        this.taskPersistencePort = taskPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void assignTaskToUser(UUID taskId, UUID userId) {
        if (taskId == null || userId == null) {
            throw new IllegalArgumentException("Task ID and User ID cannot be null.");
        }

        Optional<Task> task = taskPersistencePort.findTaskById(taskId);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }

        Optional<User> user = userPersistencePort.findUserById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }

        // Business rule: Prevent assignment of tasks that are already completed
        if (task.get().getStatus() == TaskStatus.COMPLETED) {
            throw new InvalidTaskStateException("Cannot assign a task that is already completed.");
        }

        task.get().setUserId(userId);

        try {
            taskPersistencePort.saveTask(task.get());
            logger.info("Task with ID {} assigned to user with ID {}", taskId, userId);
        } catch (Exception e) {
            logger.error("Error assigning task with ID {} to user with ID {}: {}", taskId, userId, e.getMessage(), e);
            throw new RuntimeException("Error assigning task.", e);
        }
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
