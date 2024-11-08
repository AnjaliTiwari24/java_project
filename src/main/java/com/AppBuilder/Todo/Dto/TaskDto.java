package com.AppBuilder.Todo.Dto;

import com.AppBuilder.Todo.entities.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String taskName;
    private String taskDescription;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private UserEntity userEntity;
}
