package com.quiz.Quiz_Management_System.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record AddMcqQuestionRequestDTO(
        @NotBlank String text,
        @NotNull List<String> options, // must be 4
        @NotNull Integer correctIndex
) {}
