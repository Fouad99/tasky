package com.example.tasky.infrastructure.adapter.in.rest;

import com.example.tasky.application.dto.UserDto;
import com.example.tasky.application.port.in.api.UserEndpointPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserEndpointPort userEndpointPort;

    public UserController(UserEndpointPort userEndpointPort) {
        this.userEndpointPort = userEndpointPort;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        UserDto userDto = userEndpointPort.getUserById(id);
        return userDto != null ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userEndpointPort.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        UserDto updateUserDto = userEndpointPort.updateUser(id, userDto);
        return userDto != null ? new ResponseEntity<>(updateUserDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean deleted = userEndpointPort.deleteUserById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
