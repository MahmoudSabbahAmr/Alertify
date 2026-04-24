package com.example.Alertify.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String apiKey;

    public void sendReminderEmail(String toEmail, String taskTitle, String taskAnnotation) {
        Resend resend = new Resend(apiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Alertify <onboarding@resend.dev>")
                .to(toEmail)
                .subject("⏰ Alertify Reminder: " + taskTitle)
                .html("<h2>مرحباً!</h2>" +
                        "<p>هذا تذكير بمهمتك:</p>" +
                        "<p>📌 <b>العنوان:</b> " + taskTitle + "</p>" +
                        "<p>📝 <b>الوصف:</b> " + taskAnnotation + "</p>" +
                        "<p>لا تنسى إنجاز مهمتك في وقتها! 💪</p>" +
                        "<p>Alertify Team</p>")
                .build();

        try {
            resend.emails().send(params);
        } catch (ResendException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}