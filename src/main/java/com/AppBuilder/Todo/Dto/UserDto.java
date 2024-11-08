package com.AppBuilder.Todo.Dto;

import com.AppBuilder.Todo.entities.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String password;
    private List<TaskEntity> taskEntity;
}
