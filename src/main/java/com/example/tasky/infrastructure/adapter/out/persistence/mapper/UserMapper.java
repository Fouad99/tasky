package com.example.tasky.infrastructure.adapter.out.persistence.mapper;

import com.example.tasky.domain.model.User;
import com.example.tasky.infrastructure.adapter.out.persistence.entity.UserEntity;

public class UserMapper {

    public static User toDomainModel(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getTasks().stream().map(TaskMapper::toDomainModel).toList()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getTasks().stream().map(TaskMapper::toEntity).toList()
        );
    }
}
