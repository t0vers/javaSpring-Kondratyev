package org.example.module4.service.mapper;

import org.example.module4.adapter.web.dto.UserDto;
import org.example.module4.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userToUserDto(UserEntity userEntity){
        return new UserDto(userEntity.getId(), userEntity.getName());
    }
}
