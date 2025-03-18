package com.example.tasky.application.usecase;

import com.example.tasky.domain.model.Task;

import java.util.UUID;

public interface ChangeTaskUseCase {
    Task updateTask(UUID taskId, Task task);
}
