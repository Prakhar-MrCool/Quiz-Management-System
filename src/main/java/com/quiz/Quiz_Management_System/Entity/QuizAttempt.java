package com.quiz.Quiz_Management_System.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "quiz_attempts")
@RequiredArgsConstructor
@Getter
@Setter
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quizId;

    private String username;

    private int totalQuestions;
    private int totalAnswered;
    private int correct;
    private int wrong;

    private int score;

    private Instant attemptedAt = Instant.now();
    
}

