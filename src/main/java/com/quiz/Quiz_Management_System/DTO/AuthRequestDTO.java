package com.quiz.Quiz_Management_System.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
		@NotBlank String username,
		@NotBlank String password
		) {}

