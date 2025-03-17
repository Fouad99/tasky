package com.example.tasky.application.mapper;

import com.example.tasky.application.dto.TaskDto;
import com.example.tasky.domain.model.Task;

public class TaskMapper {

    public static Task toDomainModel(TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getStatus(),
                taskDto.getCreateTime(),
                taskDto.getUpdateTime(),
                taskDto.getUserId()
        );
    }

    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getCreateTime(), task.getUpdateTime(), task.getUserId());
    }
}
