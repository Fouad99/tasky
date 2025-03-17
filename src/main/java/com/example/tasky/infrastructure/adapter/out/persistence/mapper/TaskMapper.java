package com.example.tasky.infrastructure.adapter.out.persistence.mapper;

import com.example.tasky.domain.model.Task;
import com.example.tasky.infrastructure.adapter.out.persistence.entity.TaskEntity;

public class TaskMapper {

    public static Task toDomainModel(TaskEntity taskEntity){
        return new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getStatus(),
                taskEntity.getCreateTime(),
                taskEntity.getUpdateTime(),
                taskEntity.getUser().getId()
        );
    }

    public static TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreateTime(),
                task.getUpdateTime()
        );
    }
}
