package com.example.tasky.application.port.in.api;

import java.util.UUID;

public interface TaskAssignmentEndpointPort {
    void assignTaskToUser(UUID taskId, UUID userId);
    void unassignTaskFromUser(UUID taskId);
}
