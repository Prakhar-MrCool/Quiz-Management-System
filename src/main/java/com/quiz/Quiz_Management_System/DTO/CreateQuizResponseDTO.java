package com.quiz.Quiz_Management_System.DTO;

public record CreateQuizResponseDTO(
        Long id,
        String title,
        Integer durationSeconds,
        boolean active
) {}
