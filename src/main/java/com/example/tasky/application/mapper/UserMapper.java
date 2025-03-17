package com.example.tasky.application.mapper;

import com.example.tasky.application.dto.UserDto;
import com.example.tasky.domain.model.User;

public class UserMapper {

    public static User toDomainModel(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getTasks());
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getTasks());
    }
}
