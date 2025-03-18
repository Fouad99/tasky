package com.example.tasky.infrastructure.adapter.in.impl;

import com.example.tasky.application.dto.TaskDto;
import com.example.tasky.application.port.in.api.TaskEndpointPort;
import com.example.tasky.application.usecase.ChangeTaskUseCase;
import com.example.tasky.application.usecase.FindTaskByIdUseCase;
import com.example.tasky.application.usecase.SubmitTaskUseCase;
import com.example.tasky.domain.model.Task;
import com.example.tasky.application.mapper.TaskMapper;
import com.example.tasky.infrastructure.annotations.Adapter;

import java.util.UUID;

@Adapter
public class TaskEndpointAdapter implements TaskEndpointPort {

    private final SubmitTaskUseCase submitTaskUseCase;
    private final ChangeTaskUseCase changeTaskUseCase;
    private final FindTaskByIdUseCase findTaskByIdUseCase;

    public TaskEndpointAdapter(SubmitTaskUseCase submitTaskUseCase, ChangeTaskUseCase changeTaskUseCase, FindTaskByIdUseCase findTaskByIdUseCase) {
        this.submitTaskUseCase = submitTaskUseCase;
        this.changeTaskUseCase = changeTaskUseCase;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
    }


    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = this.submitTaskUseCase.createTask(TaskMapper.toDomainModel(taskDto));
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto updateTask(UUID taskId, TaskDto taskDto) {
        Task task = TaskMapper.toDomainModel(taskDto);
        this.changeTaskUseCase.updateTask(taskId, task);
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto getTaskById(UUID taskId) {
        Task task = this.findTaskByIdUseCase.findTaskById(taskId);
        return TaskMapper.toDto(task);
    }
}
