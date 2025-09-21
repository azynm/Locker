package com.example.authproj.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authproj.dto.RegisterDTO;
import com.example.authproj.repositories.UserRepository;
import com.example.authproj.service.AuthService;

@RestController
@RequestMapping("api/auth")
public class LoginController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(AuthService authService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            String storedHash = userRepository.findByEmail(dto.getEmail()).getPassword();
            if (passwordEncoder.matches(dto.getPassword(), storedHash)) {
                return ResponseEntity.ok("Login successful");
            }
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
