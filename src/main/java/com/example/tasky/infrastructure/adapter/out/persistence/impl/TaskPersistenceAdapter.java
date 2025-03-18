package com.example.tasky.infrastructure.adapter.out.persistence.impl;

import com.example.tasky.infrastructure.adapter.out.persistence.mapper.TaskMapper;
import com.example.tasky.application.port.out.persistence.TaskPersistencePort;
import com.example.tasky.domain.model.Task;
import com.example.tasky.infrastructure.adapter.out.persistence.entity.TaskEntity;
import com.example.tasky.infrastructure.adapter.out.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskPersistenceAdapter implements TaskPersistencePort {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskPersistenceAdapter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTask(Task task) {
        TaskEntity taskEntity = taskRepository.save(TaskMapper.toEntity(task));
        return TaskMapper.toDomainModel(taskEntity);
    }

    @Override
    public Optional<Task> findTaskById(UUID taskId) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(taskId);
        return taskEntity.map(TaskMapper::toDomainModel);
    }

    @Override
    public List<Task> findAllTasks() {
        List<TaskEntity> tasksEntity = taskRepository.findAll();
        return tasksEntity.stream().map(TaskMapper::toDomainModel).toList();
    }

}
