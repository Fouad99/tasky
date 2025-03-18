package com.example.tasky.application.service.impl;

import com.example.tasky.application.port.out.persistence.UserPersistencePort;
import com.example.tasky.application.usecase.FindUserByIdUseCase;
import com.example.tasky.application.usecase.FindUsersUseCase;
import com.example.tasky.domain.exception.TaskNotFoundException;
import com.example.tasky.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindUserService implements FindUserByIdUseCase, FindUsersUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FindUserService.class);

    private final UserPersistencePort userPersistencePort;

    public FindUserService(UserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public User findUserById(UUID userId) {
        Optional<User> user = userPersistencePort.findUserById(userId);
        if (user.isEmpty()){
            throw new TaskNotFoundException("Task with ID " + userId + " not found.");
        }
        logger.info("Fetched user: {}", user);
        return user.get();
    }

    @Override
    public Collection<User> findAllUsers() {
        return userPersistencePort.findAllUsers();
    }
}
