package com.example.tasky.application.port.out.messaging;

import com.example.tasky.domain.model.Task;
import com.example.tasky.domain.model.User;

public interface MessagingPort {

    void publishTaskCreatedEvent(Task task);
    void publishTaskUpdatedEvent(Task task);
    void publishTaskCompletedEvent(Task task);
    void publishTaskAssignedEvent(Task task, User user);

}
