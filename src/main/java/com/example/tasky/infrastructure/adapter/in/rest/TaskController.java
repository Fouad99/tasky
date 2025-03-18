package com.example.tasky.infrastructure.adapter.in.rest;

import com.example.tasky.application.dto.TaskDto;
import com.example.tasky.infrastructure.adapter.in.impl.TaskEndpointAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskEndpointAdapter taskEndpointAdapter;

    public TaskController(TaskEndpointAdapter taskEndpointAdapter) {
        this.taskEndpointAdapter = taskEndpointAdapter;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable UUID taskId) {
        TaskDto taskDto = this.taskEndpointAdapter.getTaskById(taskId);
        return taskDto != null ? new ResponseEntity<>(taskDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = this.taskEndpointAdapter.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask =this.taskEndpointAdapter.updateTask(taskId, taskDto);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

}
