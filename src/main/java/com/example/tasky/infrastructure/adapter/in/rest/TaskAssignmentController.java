package com.example.tasky.infrastructure.adapter.in.rest;

import com.example.tasky.application.port.in.api.TaskAssignmentEndpointPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task-assignement")
public class TaskAssignmentController {

    private final TaskAssignmentEndpointPort taskAssignmentEndpointPort;

    @Autowired
    public TaskAssignmentController(TaskAssignmentEndpointPort taskAssignmentEndpointPort) {
        this.taskAssignmentEndpointPort = taskAssignmentEndpointPort;
    }

    @PostMapping("/assign")
    public ResponseEntity<Void> assignTaskToUser(@PathVariable UUID taskId, @PathVariable UUID userId) {
        try {
            taskAssignmentEndpointPort.assignTaskToUser(taskId, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/unassign")
    public ResponseEntity<Void> unassignTask(@PathVariable UUID taskId) {
        try {
            taskAssignmentEndpointPort.unassignTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
