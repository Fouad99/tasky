package com.example.tasky.application.port.in.api;

import com.example.tasky.application.dto.UserDto;

import java.util.UUID;

public interface UserEndpointPort {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UUID userId, UserDto userDto);
    UserDto getUserById(UUID userId);
    boolean deleteUserById(UUID userId);

}
