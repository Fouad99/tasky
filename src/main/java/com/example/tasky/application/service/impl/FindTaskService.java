package com.example.tasky.application.service.impl;


import com.example.tasky.application.port.out.persistence.TaskPersistencePort;
import com.example.tasky.application.usecase.FindTaskByIdUseCase;
import com.example.tasky.application.usecase.FindTasksUseCase;
import com.example.tasky.domain.exception.TaskNotFoundException;
import com.example.tasky.domain.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindTaskService implements FindTaskByIdUseCase, FindTasksUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FindTaskService.class);

    private final TaskPersistencePort taskPersistencePort;

    public FindTaskService(TaskPersistencePort taskPersistencePort){
        this.taskPersistencePort = taskPersistencePort;
    }


    @Override
    public Task findTaskById(UUID taskId) {
        Optional<Task> task = taskPersistencePort.findTaskById(taskId);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }
        logger.info("Fetched task: {}", task);
        return task.get();
    }

    @Override
    public Collection<Task> findAllTasks() {
        return taskPersistencePort.findAllTasks();
    }
}
