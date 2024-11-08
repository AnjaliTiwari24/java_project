package com.AppBuilder.Todo.controllers;

import com.AppBuilder.Todo.Dto.TaskDto;
import com.AppBuilder.Todo.Dto.UserDto;
import com.AppBuilder.Todo.services.TaskService;
import com.AppBuilder.Todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/tasks/{userId}")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable("userId") Long userId){
        List<TaskDto> tasks = taskService.getTaskByUserId(userId);
        return ResponseEntity.ok(tasks);
    }
    @PostMapping("/tasks/{userId}")
    public ResponseEntity<TaskDto> createTask(@PathVariable("userId") Long userId, @RequestBody TaskDto taskDto){
        TaskDto taskDto1 = taskService.createTask(userId,taskDto);
        return ResponseEntity.ok(taskDto1);
    }
    @DeleteMapping("/tasks/{taskId}/user/{userId}")
    public void deleteTask(@PathVariable("taskId") Long taskId,@PathVariable("userId") Long userId){
        taskService.deleteTask(taskId,userId);
    }
}
