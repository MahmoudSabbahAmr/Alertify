package com.example.Alertify.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendReminderEmail(String toEmail, String taskTitle, String taskAnnotation) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("⏰ Alertify Reminder: " + taskTitle);
            helper.setText(
                    "مرحباً!\n\nهذا تذكير بمهمتك:\n\n" +
                            "📌 العنوان: " + taskTitle + "\n" +
                            "📝 الوصف: " + taskAnnotation + "\n\n" +
                            "لا تنسى إنجاز مهمتك في وقتها! 💪\n\nAlertify Team"
            );
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}