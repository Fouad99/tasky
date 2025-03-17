package com.example.tasky.application.port.out.external;

import com.example.tasky.domain.model.Task;
import com.example.tasky.domain.model.User;

public interface NotificationPort {
    void sendTaskAssignedNotification(Task task, User user);
    void sendTaskCompletedNotification(Task task);
}
