package com.example.tasky.infrastructure.adapter.in.rest;

import com.example.tasky.infrastructure.adapter.in.impl.TaskAssignmentEndpointAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task-assignement")
public class TaskAssignmentController {

    private final TaskAssignmentEndpointAdapter taskAssignmentEndpointAdapter;

    @Autowired
    public TaskAssignmentController(TaskAssignmentEndpointAdapter taskAssignmentEndpointAdapter) {
        this.taskAssignmentEndpointAdapter = taskAssignmentEndpointAdapter;
    }

    @PostMapping("/assign")
    public ResponseEntity<Void> assignTaskToUser(@PathVariable UUID taskId, @PathVariable UUID userId) {
        try {
            this.taskAssignmentEndpointAdapter.assignTaskToUser(taskId, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/unassign")
    public ResponseEntity<Void> unassignTask(@PathVariable UUID taskId) {
        try {
            this.taskAssignmentEndpointAdapter.unassignTaskFromUser(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
