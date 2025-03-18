package com.example.tasky.application.usecase;

import com.example.tasky.domain.model.User;

import java.util.UUID;

public interface FindUserByIdUseCase {
    User findUserById(UUID userId);
}
