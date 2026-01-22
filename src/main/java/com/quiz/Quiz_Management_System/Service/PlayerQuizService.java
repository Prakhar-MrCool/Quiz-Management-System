package com.quiz.Quiz_Management_System.Service;

import com.quiz.Quiz_Management_System.DTO.*;
import com.quiz.Quiz_Management_System.Entity.*;
import com.quiz.Quiz_Management_System.Repository.QuizAttemptRepository;
import com.quiz.Quiz_Management_System.Repository.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerQuizService {

    private final QuizRepository quizRepository;
    private final QuizAttemptRepository attemptRepository;


    public List<QuizListResponseDTO> listActiveQuizzes() {
        return quizRepository.findByActiveTrue()
                .stream()
                .map(q -> new QuizListResponseDTO(q.getId(), q.getTitle(), q.getDurationSeconds()))
                .collect(Collectors.toList());
    }

    public List<QuestionResponseDTO> getQuizQuestions(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        return quiz.getQuestions().stream()
                .map(q -> new QuestionResponseDTO(
                        q.getId(),
                        q.getText(),
                        q.getType().name(),
                        q.getOptions().stream()
                                .map(o -> new OptionResponseDTO(o.getId(), o.getOptionText()))
                                .toList()
                ))
                .toList();
    }

    @Transactional
    public QuizSubmissionResponseDTO submitQuiz(Long quizId,
                                            String username,
                                            QuizSubmissionRequestDTO req) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<Question> questions = quiz.getQuestions();

        int totalQuestions = questions.size();
        int totalAnswered = 0;
        int correct = 0;
        int wrong = 0;

        for (Question q : questions) {
            String answer = req.answers().get(q.getId());
            if (answer == null || answer.isBlank()) continue;

            totalAnswered++;

            boolean isCorrect = switch (q.getType()) {
                case MCQ -> {
                    Long selectedOptionId = Long.valueOf(answer);
                    yield selectedOptionId.equals(q.getCorrectOptionId());
                }
                case TRUE_FALSE, TEXT ->
                        q.getCorrectAnswer() != null &&
                        q.getCorrectAnswer().trim().equalsIgnoreCase(answer.trim());
            };

            if (isCorrect) correct++;
            else wrong++;
        }

        int score = correct; // simple scoring

        QuizAttempt attempt = new QuizAttempt();
        attempt.setQuizId(quizId);
        attempt.setUsername(username);
        attempt.setTotalQuestions(totalQuestions);
        attempt.setTotalAnswered(totalAnswered);
        attempt.setCorrect(correct);
        attempt.setWrong(wrong);
        attempt.setScore(score);

        attemptRepository.save(attempt);

        return new QuizSubmissionResponseDTO(totalQuestions, totalAnswered, correct, wrong, score);
    }

    public List<LeaderboardRowDTO> getLeaderboard(Long quizId) {
        return attemptRepository.findByQuizIdOrderByScoreDesc(quizId)
                .stream()
                .map(a -> new LeaderboardRowDTO(a.getUsername(), a.getScore()))
                .toList();
    }
}
