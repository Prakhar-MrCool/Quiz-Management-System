package com.quiz.Quiz_Management_System.DTO;

public record QuizListResponseDTO(
        Long id,
        String title,
        Integer durationSeconds
) {}
