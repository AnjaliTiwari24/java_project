package com.AppBuilder.Todo.controllers;

import com.AppBuilder.Todo.Dto.UserDto;
import com.AppBuilder.Todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long userId) {
        Optional<UserDto> user = userService.getUser(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/user/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto).getBody();
        return ResponseEntity.ok(user);
    }
    @DeleteMapping(path = "/user/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

}
