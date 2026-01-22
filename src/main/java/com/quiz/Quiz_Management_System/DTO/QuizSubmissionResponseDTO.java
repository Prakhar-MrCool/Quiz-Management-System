package com.quiz.Quiz_Management_System.DTO;

public record QuizSubmissionResponseDTO(
        int totalQuestions,
        int totalAnswered,
        int correct,
        int wrong,
        int score
) {}
