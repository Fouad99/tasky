package com.example.tasky.infrastructure.adapter.out.persistence.repository;

import com.example.tasky.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
