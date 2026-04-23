package com.example.Alertify.Repo;

import com.example.Alertify.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByIsDoneFalseAndAlertTimeBefore(LocalDateTime time);
    List<Task> findByUserEmail(String email);
}