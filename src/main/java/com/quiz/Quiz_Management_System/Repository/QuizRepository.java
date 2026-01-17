package com.quiz.Quiz_Management_System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.Quiz_Management_System.Entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByActiveTrue();
}
