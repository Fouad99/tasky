package com.example.tasky.application.service.impl;

import com.example.tasky.application.port.out.persistence.TaskPersistencePort;
import com.example.tasky.application.usecase.SubmitTaskUseCase;
import com.example.tasky.domain.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubmitTaskService implements SubmitTaskUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SubmitTaskService.class);

    private final TaskPersistencePort taskPersistencePort;

    public SubmitTaskService(TaskPersistencePort taskPersistencePort){
        this.taskPersistencePort = taskPersistencePort;
    }

    @Override
    public Task createTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (task.getDescription() == null || task.getDescription().isEmpty()){
            throw new IllegalArgumentException("Description cannot be empty");
        }

        Task newTask = new Task(task.getTitle(), task.getDescription());

        try {
            taskPersistencePort.saveTask(newTask);
            logger.info("Task created successfully: {}", task);
            return newTask;
        }catch (Exception e){
            logger.error("Error creating task: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating task.", e);
        }
    }
}
