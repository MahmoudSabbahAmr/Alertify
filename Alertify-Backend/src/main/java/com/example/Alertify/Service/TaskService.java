package com.example.Alertify.Service;

import com.example.Alertify.Dto.TaskDTO;
import com.example.Alertify.Entity.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskDTO dto);


    Task updateTask(Long id, TaskDTO dto);

    void deleteTask(Long id);

    List<Task> getTasksByEmail(String email);
}
