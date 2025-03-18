package com.example.tasky.application.port.out.persistence;

import com.example.tasky.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPersistencePort {
    User saveUser(User user);
    Optional<User> findUserById(UUID userId);
    List<User> findAllUsers();
}
