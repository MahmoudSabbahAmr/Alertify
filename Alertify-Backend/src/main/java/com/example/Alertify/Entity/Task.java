package com.example.Alertify.Entity;

import com.example.Alertify.Enum.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String annotation;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime alertTime;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDone = false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}