package com.example.tasky.infrastructure.adapter.in.impl;

import com.example.tasky.application.dto.UserDto;
import com.example.tasky.application.mapper.UserMapper;
import com.example.tasky.application.port.in.api.UserEndpointPort;
import com.example.tasky.application.usecase.ChangeUserUseCase;
import com.example.tasky.application.usecase.FindUserByIdUseCase;
import com.example.tasky.application.usecase.SubmitUserUseCase;
import com.example.tasky.domain.model.User;

import java.util.UUID;

public class UserEndpointAdapter implements UserEndpointPort {

    private final SubmitUserUseCase submitUserUseCase;
    private final ChangeUserUseCase changeUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;

    public UserEndpointAdapter(SubmitUserUseCase submitUserUseCase, ChangeUserUseCase changeUserUseCase, FindUserByIdUseCase findUserByIdUseCase) {
        this.submitUserUseCase = submitUserUseCase;
        this.changeUserUseCase = changeUserUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.submitUserUseCase.createUser(UserMapper.toDomainModel(userDto));
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        User user = this.changeUserUseCase.updateUser(userId, UserMapper.toDomainModel(userDto));
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto getUserById(UUID userId) {
        return UserMapper.toDto(this.findUserByIdUseCase.findUserById(userId));
    }
}
