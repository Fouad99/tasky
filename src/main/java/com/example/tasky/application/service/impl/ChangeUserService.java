package com.example.tasky.application.service.impl;

import com.example.tasky.application.port.out.persistence.UserPersistencePort;
import com.example.tasky.application.usecase.ChangeUserUseCase;
import com.example.tasky.domain.exception.TaskNotFoundException;
import com.example.tasky.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChangeUserService implements ChangeUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ChangeUserService.class);

    private final UserPersistencePort userPersistencePort;

    public ChangeUserService(UserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public User updateUser(UUID userId, User user) {
        Optional<User> existingUser = userPersistencePort.findUserById(userId);

        if (existingUser.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + userId + " not found.");
        }

        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setUsername(user.getUsername());

        try {
            userPersistencePort.saveUser(existingUser.get());
            logger.info("User updated successfully: {}", user);
            return existingUser.get();
        } catch (Exception e){
            logger.error("Error updating user: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating user.", e);
        }
    }
}
