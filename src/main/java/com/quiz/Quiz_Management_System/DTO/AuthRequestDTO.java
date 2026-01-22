package com.quiz.Quiz_Management_System.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
		@NotBlank String username,
		@NotBlank String password
		) {}

