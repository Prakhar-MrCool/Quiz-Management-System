package com.quiz.Quiz_Management_System.Controller;

import com.quiz.Quiz_Management_System.DTO.*;
import com.quiz.Quiz_Management_System.Service.PlayerQuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@PreAuthorize("hasRole('PLAYER')")
@RequiredArgsConstructor
public class PlayerQuizController {

    private final PlayerQuizService playerQuizService;

    @GetMapping("/quizzes")
    public List<QuizListResponseDTO> list() {
        return playerQuizService.listActiveQuizzes();
    }

    @GetMapping("/quizzes/{quizId}/questions")
    public List<QuestionResponseDTO> questions(@PathVariable Long quizId) {
        return playerQuizService.getQuizQuestions(quizId);
    }

    @PostMapping("/quizzes/{quizId}/submit")
    public QuizSubmissionResponseDTO submit(@PathVariable Long quizId,
                                         @Valid @RequestBody QuizSubmissionRequestDTO req,
                                         Authentication auth) {
        return playerQuizService.submitQuiz(quizId, auth.getName(), req);
    }

    @GetMapping("/quizzes/{quizId}/leaderboard")
    public List<LeaderboardRowDTO> leaderboard(@PathVariable Long quizId) {
        return playerQuizService.getLeaderboard(quizId);
    }
}
