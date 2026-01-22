package com.quiz.Quiz_Management_System.DTO;

import java.util.List;

public record QuestionResponseDTO(
        Long id,
        String text,
        String type,
        List<OptionResponseDTO> options
) {}
