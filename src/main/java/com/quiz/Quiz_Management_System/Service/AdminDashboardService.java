package com.quiz.Quiz_Management_System.Service;

import com.quiz.Quiz_Management_System.DTO.*;
import com.quiz.Quiz_Management_System.Entity.Quiz;
import com.quiz.Quiz_Management_System.Repository.QuizAttemptRepository;
import com.quiz.Quiz_Management_System.Repository.QuizRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final QuizRepository quizRepository;
    private final QuizAttemptRepository attemptRepository;


    public QuizDashboardResponseDTO getDashboard(Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<LeaderboardRowDTO> leaderboard = attemptRepository
                .findByQuizIdOrderByScoreDesc(quizId)
                .stream()
                .map(a -> new LeaderboardRowDTO(a.getUsername(), a.getScore()))
                .toList();

        return new QuizDashboardResponseDTO(
                quiz.getId(),
                quiz.getTitle(),
                leaderboard.size(),
                leaderboard
        );
    }
}
