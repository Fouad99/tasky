package com.example.tasky.infrastructure.adapter.in.impl;

import com.example.tasky.application.port.in.api.TaskAssignmentEndpointPort;
import com.example.tasky.application.usecase.AssignTaskUseCase;
import com.example.tasky.application.usecase.UnassignTaskUseCase;
import com.example.tasky.infrastructure.annotations.Adapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Adapter
public class TaskAssignmentEndpointAdapter implements TaskAssignmentEndpointPort {

    private final AssignTaskUseCase assignTaskUseCase;
    private final UnassignTaskUseCase unassignTaskUseCase;

    @Autowired
    public TaskAssignmentEndpointAdapter(AssignTaskUseCase assignTaskUseCase, UnassignTaskUseCase unassignTaskUseCase) {
        this.assignTaskUseCase = assignTaskUseCase;
        this.unassignTaskUseCase = unassignTaskUseCase;
    }


    @Override
    public void assignTaskToUser(UUID taskId, UUID userId) {
        this.assignTaskUseCase.assignTask(taskId, userId);
    }

    @Override
    public void unassignTaskFromUser(UUID taskId) {
       this.unassignTaskUseCase.unassignTask(taskId);
    }
}
