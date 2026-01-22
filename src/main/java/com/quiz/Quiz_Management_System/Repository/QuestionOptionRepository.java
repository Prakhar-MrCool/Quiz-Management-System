package com.quiz.Quiz_Management_System.Repository;

import com.quiz.Quiz_Management_System.Entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    List<QuestionOption> findByQuestionId(Long questionId);
}
