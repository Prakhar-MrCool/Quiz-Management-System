package com.quiz.Quiz_Management_System.Controller;

import com.quiz.Quiz_Management_System.DTO.*;
import com.quiz.Quiz_Management_System.Entity.Question;
import com.quiz.Quiz_Management_System.Entity.Quiz;
import com.quiz.Quiz_Management_System.Service.AdminDashboardService;
import com.quiz.Quiz_Management_System.Service.QuizAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminQuizController {

    private final QuizAdminService quizAdminService;
    private final AdminDashboardService adminDashboardService;


	/*
	 * @PostMapping("/quizzes") public Quiz createQuiz(@Valid @RequestBody
	 * CreateQuizRequestDTO req) { return quizAdminService.createQuiz(req); }
	 */
    
    @PostMapping("/quizzes")
    public ResponseEntity<CreateQuizResponseDTO> createQuiz(
            @Valid @RequestBody CreateQuizRequestDTO req) {

        Quiz quiz = quizAdminService.createQuiz(req);

        return ResponseEntity.ok(new CreateQuizResponseDTO(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDurationSeconds(),
                quiz.isActive()
        ));
    }




	/*
	 * @PostMapping("/quizzes/{quizId}/questions/mcq") public Question
	 * addMcq(@PathVariable Long quizId,
	 * 
	 * @Valid @RequestBody AddMcqQuestionRequestDTO req) { return
	 * quizAdminService.addMcqQuestion(quizId, req); }
	 */
    
    @PostMapping("/quizzes/{quizId}/questions/mcq")
    public ResponseEntity<AddQuestionResponseDTO> addMcq(
            @PathVariable Long quizId,
            @Valid @RequestBody AddMcqQuestionRequestDTO req) {

    	AddQuestionResponseDTO response = quizAdminService.addMcqQuestion(quizId, req);
    	return ResponseEntity.ok(response);

    }


    @GetMapping("/quizzes/{quizId}/dashboard")
    public QuizDashboardResponseDTO dashboard(@PathVariable Long quizId) {
        return adminDashboardService.getDashboard(quizId);
    }
}
