package com.example.tasky.infrastructure.adapter.in.rest;

import com.example.tasky.application.dto.UserDto;
import com.example.tasky.infrastructure.adapter.in.impl.UserEndpointAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserEndpointAdapter userEndpointAdapter;

    public UserController(UserEndpointAdapter userEndpointAdapter) {
        this.userEndpointAdapter = userEndpointAdapter;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        UserDto userDto = this.userEndpointAdapter.getUserById(id);
        return userDto != null ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userEndpointAdapter.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        UserDto updateUserDto = this.userEndpointAdapter.updateUser(id, userDto);
        return userDto != null ? new ResponseEntity<>(updateUserDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
