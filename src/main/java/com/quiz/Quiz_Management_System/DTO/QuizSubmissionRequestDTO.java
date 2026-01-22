package com.quiz.Quiz_Management_System.DTO;

import java.util.Map;

// key = questionId, value = selectedOptionId (as string) OR answer text for text questions
public record QuizSubmissionRequestDTO(
        Map<Long, String> answers
) {}
