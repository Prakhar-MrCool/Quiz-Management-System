package com.quiz.Quiz_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.Quiz_Management_System.DTO.AuthRequest;
import com.quiz.Quiz_Management_System.DTO.AuthResponse;
import com.quiz.Quiz_Management_System.Service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login( @Valid
            @RequestBody AuthRequest request) {

        String token = authService.login(request.username(), request.password());
        System.out.println("token "+token);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
