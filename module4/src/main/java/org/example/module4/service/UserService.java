package org.example.module4.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.module4.adapter.repository.UserRepository;
import org.example.module4.adapter.web.dto.UserDto;
import org.example.module4.adapter.web.error.NotFoundException;
import org.example.module4.domain.UserEntity;
import org.example.module4.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserEntity user : users) {
            userDtoList.add(userMapper.userToUserDto(user));
        }
        return userDtoList;
    }

    public UserDto add(String name) {
        UserEntity user = new UserEntity(name);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    public UserDto getById(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException("not found user by id");
        }
        return userMapper.userToUserDto(user.get());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
