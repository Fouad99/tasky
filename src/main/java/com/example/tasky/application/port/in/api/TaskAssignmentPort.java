package com.example.tasky.application.port.in.api;

import java.util.UUID;

public interface TaskAssignmentPort {
    void assignTaskToUser(UUID taskId, UUID userId);
    void unassignTask(UUID taskId);
}
