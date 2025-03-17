package com.example.tasky.application.port.in.api;

import com.example.tasky.application.dto.AssignTaskDto;

import java.util.UUID;

public interface TaskAssignmentEndpointPort {
    void assignTaskToUser(UUID taskId, UUID userId);
    void unassignTask(UUID taskId);
}
