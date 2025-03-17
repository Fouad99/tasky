package com.example.tasky.infrastructure.adapter.out.persistence.repository;

import com.example.tasky.infrastructure.adapter.out.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

}
