package com.quiz.Quiz_Management_System.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateQuizRequestDTO(
        @NotBlank String title,
        @NotNull Integer durationSeconds,
        boolean active
) {}
