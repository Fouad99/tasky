package com.example.tasky.application.service.impl;

import com.example.tasky.application.dto.TaskDto;
import com.example.tasky.application.mapper.TaskMapper;
import com.example.tasky.application.port.in.api.TaskEndpointPort;
import com.example.tasky.application.port.out.persistence.TaskPersistencePort;
import com.example.tasky.application.port.out.persistence.UserPersistencePort;
import com.example.tasky.domain.exception.InvalidTaskStateException;
import com.example.tasky.domain.exception.TaskNotFoundException;
import com.example.tasky.domain.model.Task;
import com.example.tasky.domain.model.TaskStatus;
import com.example.tasky.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskManagementService implements TaskEndpointPort {

    private static final Logger logger = LoggerFactory.getLogger(TaskManagementService.class);

    private final TaskPersistencePort taskPersistencePort;
    private final UserPersistencePort userPersistencePort;

    public TaskManagementService(TaskPersistencePort taskPersistencePort, UserPersistencePort userPersistencePort){
        this.taskPersistencePort = taskPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public TaskDto createTask(TaskDto taskDto) {
        if (taskDto.getTitle() == null || taskDto.getTitle().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (taskDto.getDescription() == null || taskDto.getDescription().isEmpty()){
            throw new IllegalArgumentException("Description cannot be empty");
        }

        Optional<User> user = userPersistencePort.findUserById(taskDto.getUserId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + taskDto.getUserId() + " does not exist.");
        }

        Task task = new Task(taskDto.getTitle(), taskDto.getDescription());
        task.setUserId(taskDto.getUserId());

        try {
            taskPersistencePort.saveTask(task);
            logger.info("Task created successfully: {}", task);
            return TaskMapper.toDto(task);
        }catch (Exception e){
            logger.error("Error creating task: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating task.", e);
        }
    }

    @Override
    public TaskDto updateTask(UUID taskId, TaskDto taskDto) {
        Optional<Task> task = taskPersistencePort.findTaskById(taskId);

        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }

        if (task.get().getStatus() == TaskStatus.COMPLETED) {
            throw new InvalidTaskStateException("Task is already completed and cannot be updated.");
        }

        task.get().updateStatus(taskDto.getStatus());


        try {
            taskPersistencePort.saveTask(task.get());
            logger.info("Task updated successfully: {}", task);
            return TaskMapper.toDto(task.get());
        } catch (Exception e) {
            logger.error("Error updating task: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating task.", e);
        }
    }

    @Override
    public TaskDto getTaskById(UUID taskId) {
        Optional<Task> task = taskPersistencePort.findTaskById(taskId);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }
        logger.info("Fetched task: {}", task);
        return TaskMapper.toDto(task.get());
    }

    @Override
    public void deleteTask(UUID taskId) {
        Optional<Task> task = taskPersistencePort.findTaskById(taskId);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }

        if (task.get().getStatus() == TaskStatus.COMPLETED) {
            throw new InvalidTaskStateException("Completed tasks cannot be deleted.");
        }

        try {
            taskPersistencePort.deleteTask(taskId);
            logger.info("Task deleted successfully with ID: {}", taskId);
        } catch (Exception e) {
            logger.error("Error deleting task: {}", e.getMessage(), e);
            throw new RuntimeException("Error deleting task.", e);
        }
    }
}
