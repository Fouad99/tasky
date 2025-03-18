package com.example.tasky.application.usecase;

import com.example.tasky.domain.model.Task;

import java.util.UUID;

public interface FindTaskByIdUseCase {
    Task findTaskById(UUID taskId);
}
