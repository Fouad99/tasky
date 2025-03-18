package com.example.tasky.application.usecase;

import com.example.tasky.domain.model.Task;

import java.util.Collection;

public interface FindTasksUseCase {
    Collection<Task> findAllTasks();
}
