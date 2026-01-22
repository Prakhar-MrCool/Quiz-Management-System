package com.quiz.Quiz_Management_System.DTO;

import java.util.List;

public record QuizDashboardResponseDTO(
        Long quizId,
        String title,
        int totalAttempts,
        List<LeaderboardRowDTO> leaderboard
) {}
