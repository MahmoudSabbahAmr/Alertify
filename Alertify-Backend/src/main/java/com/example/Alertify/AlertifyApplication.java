package com.example.Alertify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlertifyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlertifyApplication.class, args);
    }
}