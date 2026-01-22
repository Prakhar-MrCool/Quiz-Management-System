package com.quiz.Quiz_Management_System.DTO;

public record AddQuestionResponseDTO(
        Long questionId,
        Long quizId,
        String message
) {}
