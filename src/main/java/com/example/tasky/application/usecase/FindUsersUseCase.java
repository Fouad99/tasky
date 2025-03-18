package com.example.tasky.application.usecase;

import com.example.tasky.domain.model.User;

import java.util.Collection;

public interface FindUsersUseCase {
    Collection<User> findAllUsers();
}
