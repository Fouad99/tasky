package com.example.tasky.infrastructure.adapter.out.persistence.impl;

import com.example.tasky.application.port.out.persistence.UserPersistencePort;
import com.example.tasky.domain.model.User;
import com.example.tasky.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.example.tasky.infrastructure.adapter.out.persistence.mapper.UserMapper;
import com.example.tasky.infrastructure.adapter.out.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomainModel(userEntity);
    }

    @Override
    public Optional<User> findUserById(UUID userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntity.map(UserMapper::toDomainModel);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(UserMapper::toDomainModel).toList();
    }
}
