package com.example.tasky.application.usecase;

import com.example.tasky.domain.model.Task;

public interface SubmitTaskUseCase {
    Task createTask(Task task);
}
