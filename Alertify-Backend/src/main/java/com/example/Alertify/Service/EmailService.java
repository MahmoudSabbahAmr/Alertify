package com.example.Alertify.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReminderEmail(String toEmail, String taskTitle, String taskAnnotation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("⏰ Alertify Reminder: " + taskTitle);
        message.setText(
                "مرحباً!\n\n" +
                        "هذا تذكير بمهمتك:\n\n" +
                        "📌 العنوان: " + taskTitle + "\n" +
                        "📝 الوصف: " + taskAnnotation + "\n\n" +
                        "لا تنسى إنجاز مهمتك في وقتها! 💪\n\n" +
                        "Alertify Team"
        );
        mailSender.send(message);
    }
}