package com.AppBuilder.Todo.services;

import com.AppBuilder.Todo.Dto.TaskDto;
import com.AppBuilder.Todo.entities.TaskEntity;
import com.AppBuilder.Todo.entities.UserEntity;
import com.AppBuilder.Todo.exceptions.TaskException;
import com.AppBuilder.Todo.repositories.TaskRepository;
import com.AppBuilder.Todo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TaskDto> getTaskByUserId(Long userId) {
        List<TaskEntity> tasks = taskRepository.findByUserEntityId(userId);
        return tasks.stream()
                .map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class))
                .collect(Collectors.toList());
    }

    public TaskDto createTask(Long userId, TaskDto taskDto) {
        Optional<UserEntity> user = userRepository.findById(userId);
        TaskEntity taskEntity = modelMapper.map(taskDto, TaskEntity.class);
        if(user.isEmpty()){
            return null;
        }
        else {
            taskEntity.setUserEntity(user.get());
            taskEntity = taskRepository.save(taskEntity);
            return modelMapper.map(taskEntity, TaskDto.class);
        }
    }

    public void deleteTask(Long taskId,Long userId) {
       List<TaskEntity> tasks = taskRepository.findByUserEntityId(userId);
        TaskEntity task = tasks.stream()
                .filter(taskEntity -> taskEntity.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskException("Task not found for given ID"));
       taskRepository.delete(task);
    }
}
