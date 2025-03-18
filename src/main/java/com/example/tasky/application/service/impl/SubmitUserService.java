package com.example.tasky.application.service.impl;

import com.example.tasky.application.port.out.persistence.UserPersistencePort;
import com.example.tasky.application.usecase.SubmitUserUseCase;
import com.example.tasky.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubmitUserService implements SubmitUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SubmitUserService.class);

    private final UserPersistencePort userPersistencePort;

    public SubmitUserService(UserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User createUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()){
            throw new IllegalArgumentException("username cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()){
            throw new IllegalArgumentException("email cannot be empty");
        }

        User newUser = new User(user.getUsername(), user.getEmail());

        try {
            userPersistencePort.saveUser(user);
            logger.info("User created successfully: {}", user);
            return newUser;
        } catch (Exception e){
            logger.error("Error creating user: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating user.", e);
        }
    }
}
