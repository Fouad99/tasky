package com.example.tasky.application.port.out.persistence;

import com.example.tasky.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskPersistencePort {
    Task saveTask(Task task);
    Optional<Task> findTaskById(UUID taskId);
    List<Task> findAllTasks();
}
