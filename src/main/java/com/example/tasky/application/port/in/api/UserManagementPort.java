package com.example.tasky.application.port.in.api;

import com.example.tasky.domain.model.User;

import java.util.UUID;

public interface UserManagementPort {

    User createUser(String username, String email);
    User updateUser(UUID userId, String username, String email);
    User getUserById(UUID userId);
    void deleteUserById(UUID userId);
}
