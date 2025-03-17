package com.example.tasky.infrastructure.adapter.in.rest;

import com.example.tasky.application.dto.TaskDto;
import com.example.tasky.application.port.in.api.TaskEndpointPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskEndpointPort taskEndpointPort;

    public TaskController(TaskEndpointPort taskEndpointPort) {
        this.taskEndpointPort = taskEndpointPort;
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable UUID taskId) {
        TaskDto taskDto = taskEndpointPort.getTaskById(taskId);
        return taskDto != null ? new ResponseEntity<>(taskDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskEndpointPort.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskEndpointPort.updateTask(taskId, taskDto);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskEndpointPort.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
