package com.quiz.Quiz_Management_System.Repository;

import com.quiz.Quiz_Management_System.Entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByQuizIdOrderByScoreDesc(Long quizId);
    List<QuizAttempt> findByUsernameOrderByAttemptedAtDesc(String username);
}
