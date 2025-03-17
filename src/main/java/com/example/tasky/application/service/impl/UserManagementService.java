package com.example.tasky.application.service.impl;

import com.example.tasky.application.dto.UserDto;
import com.example.tasky.application.mapper.UserMapper;
import com.example.tasky.application.port.in.api.UserEndpointPort;
import com.example.tasky.application.port.out.persistence.UserPersistencePort;
import com.example.tasky.domain.exception.TaskNotFoundException;
import com.example.tasky.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserManagementService implements UserEndpointPort {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

    private final UserPersistencePort userPersistencePort;

    public UserManagementService(UserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()){
            throw new IllegalArgumentException("username cannot be empty");
        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()){
            throw new IllegalArgumentException("email cannot be empty");
        }

        User user = new User(userDto.getUsername(), userDto.getEmail());

        try {
            userPersistencePort.saveUser(user);
            logger.info("User created successfully: {}", user);
            return UserMapper.toDto(user);
        } catch (Exception e){
            logger.error("Error creating user: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating user.", e);
        }
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        Optional<User> user = userPersistencePort.findUserById(userId);

        if (user.isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + userId + " not found.");
        }

        user.get().setEmail(userDto.getEmail());
        user.get().setUsername(userDto.getUsername());

        try {
            userPersistencePort.saveUser(user.get());
            logger.info("User updated successfully: {}", user);
            return UserMapper.toDto(user.get());
        } catch (Exception e){
            logger.error("Error updating user: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating user.", e);
        }
    }

    @Override
    public UserDto getUserById(UUID userId) {
        Optional<User> user = userPersistencePort.findUserById(userId);
        if (user.isEmpty()){
            throw new TaskNotFoundException("Task with ID " + userId + " not found.");
        }
        logger.info("Fetched user: {}", user);
        return UserMapper.toDto(user.get());
    }

    @Override
    public boolean deleteUserById(UUID userId) {
        Optional<User> user = userPersistencePort.findUserById(userId);
        if (user.isEmpty()){
            throw new TaskNotFoundException("Task with ID " + userId + " not found.");
        }
        try {
            userPersistencePort.deleteUser(userId);
            logger.info("User deleted successfully with ID: {}", userId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting user: {}", e.getMessage(), e);
            throw new RuntimeException("Error deleting user.", e);
        }
    }
}
