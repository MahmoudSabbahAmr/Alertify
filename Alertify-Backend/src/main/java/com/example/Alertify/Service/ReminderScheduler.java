package com.example.Alertify.Service;

import com.example.Alertify.Entity.Task;
import com.example.Alertify.Repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void checkAndSendReminders() {

        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskRepository.findByIsDoneFalseAndAlertTimeBefore(now);

        for (Task task : tasks) {
            String email = task.getUser() != null
                    ? task.getUser().getEmail()
                    : null;

            if (email != null) {
                emailService.sendReminderEmail(
                        email,
                        task.getTitle(),
                        task.getAnnotation()
                );
            }
            task.setIsDone(true);
            taskRepository.save(task);
        }
    }
}