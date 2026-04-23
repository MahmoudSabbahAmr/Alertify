package com.example.Alertify.Dto;

import com.example.Alertify.Enum.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String title;
    private String annotation;
    private Priority priority;
    private LocalDateTime alertTime;
    private Boolean isDone = false;
    private String userEmail;
}