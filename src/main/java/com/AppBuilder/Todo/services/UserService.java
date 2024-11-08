package com.AppBuilder.Todo.services;

import com.AppBuilder.Todo.Dto.UserDto;
import com.AppBuilder.Todo.entities.UserEntity;
import com.AppBuilder.Todo.exceptions.UserException;
import com.AppBuilder.Todo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<UserDto> getUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found with ID: " + userId));
        return Optional.ofNullable(modelMapper.map(userEntity, UserDto.class));
    }

    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
        userEntity = userRepository.save(userEntity);
        userDto = modelMapper.map(userEntity, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found with ID: " + userId));
        userRepository.delete(userEntity);
    }
}
