package com.quiz.Quiz_Management_System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.Quiz_Management_System.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	List<Question> findByQuizId(Long quizId);

}
