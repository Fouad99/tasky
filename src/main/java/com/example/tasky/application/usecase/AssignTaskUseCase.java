package com.example.tasky.application.usecase;

import java.util.UUID;

public interface AssignTaskUseCase {
    void assignTask(UUID taskId, UUID userId);
}
