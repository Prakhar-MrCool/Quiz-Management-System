package com.quiz.Quiz_Management_System.Controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev")
public class DevController {

    private final PasswordEncoder encoder;

    @GetMapping("/encode")
    public String encode(@RequestParam String raw) {
        return "{bcrypt}" + encoder.encode(raw);
    }
}

