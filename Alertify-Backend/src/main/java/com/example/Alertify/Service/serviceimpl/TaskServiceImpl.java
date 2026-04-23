package com.example.Alertify.Service.serviceimpl;

import com.example.Alertify.Dto.TaskDTO;
import com.example.Alertify.Entity.Task;
import com.example.Alertify.Entity.User;
import com.example.Alertify.Repo.TaskRepository;
import com.example.Alertify.Repo.UserRepository;
import com.example.Alertify.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Task createTask(TaskDTO dto) {
        Task task = mapToEntity(dto);
        if (dto.getUserEmail() != null) {
            User user = userRepository.findByEmail(dto.getUserEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            task.setUser(user);
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByEmail(String email) {
        return taskRepository.findByUserEmail(email);
    }

    @Override
    public Task updateTask(Long id, TaskDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        task.setTitle(dto.getTitle());
        task.setAnnotation(dto.getAnnotation());
        task.setPriority(dto.getPriority());
        task.setAlertTime(dto.getAlertTime());
        task.setIsDone(dto.getIsDone() != null ? dto.getIsDone() : false);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    private Task mapToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setAnnotation(dto.getAnnotation());
        task.setPriority(dto.getPriority());
        task.setAlertTime(dto.getAlertTime());
        task.setIsDone(dto.getIsDone() != null ? dto.getIsDone() : false);
        return task;
    }
}