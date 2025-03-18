package com.example.tasky.application.usecase;

import com.example.tasky.domain.model.User;

public interface SubmitUserUseCase {
    User createUser(User user);
}
