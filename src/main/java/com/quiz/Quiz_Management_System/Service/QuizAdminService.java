package com.quiz.Quiz_Management_System.Service;

import com.quiz.Quiz_Management_System.DTO.AddMcqQuestionRequestDTO;
import com.quiz.Quiz_Management_System.DTO.AddQuestionResponseDTO;
import com.quiz.Quiz_Management_System.DTO.CreateQuizRequestDTO;
import com.quiz.Quiz_Management_System.Entity.*;
import com.quiz.Quiz_Management_System.Repository.QuestionRepository;
import com.quiz.Quiz_Management_System.Repository.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizAdminService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public Quiz createQuiz(CreateQuizRequestDTO req) {
        Quiz quiz = new Quiz();
        quiz.setTitle(req.title());
        quiz.setDurationSeconds(req.durationSeconds());
        quiz.setActive(req.active());
        return quizRepository.save(quiz);
    }

    @Transactional
    public AddQuestionResponseDTO addMcqQuestion(Long quizId, AddMcqQuestionRequestDTO req) {

        if (req.options() == null || req.options().size() != 4) {
            throw new IllegalArgumentException("MCQ must have exactly 4 options");
        }
        if (req.correctIndex() < 0 || req.correctIndex() > 3) {
            throw new IllegalArgumentException("correctIndex must be 0..3");
        }

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question q = new Question();
        q.setQuiz(quiz);
        q.setText(req.text());
        q.setType(QuestionType.MCQ);

        for (String optionText : req.options()) {
            QuestionOption opt = new QuestionOption();
            opt.setQuestion(q);
            opt.setOptionText(optionText);
            q.getOptions().add(opt);
        }

        // ✅ Save Question directly -> generates ID immediately
        Question savedQuestion = questionRepository.save(q);

        // now option ids exist
        Long correctOptionId = savedQuestion.getOptions()
                .get(req.correctIndex())
                .getId();
        savedQuestion.setCorrectOptionId(correctOptionId);

        // ✅ update correctOptionId
        questionRepository.save(savedQuestion);

        return new AddQuestionResponseDTO(savedQuestion.getId(), quizId, "MCQ Question Added Successfully");
    }
}
