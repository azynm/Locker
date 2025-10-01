package com.example.authproj.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authproj.dto.LoginDTO;
import com.example.authproj.dto.TokenResponse;
import com.example.authproj.repositories.UserRepository;
import com.example.authproj.security.JWTService;

@RestController
@RequestMapping("api/auth")
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public LoginController(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDTO dto) {
        var user = userRepository.findByEmail(dto.getEmail());

        if (user != null && passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getEmail());
            TokenResponse tokenResponse = new TokenResponse(token);
            return ResponseEntity.ok(tokenResponse);
        }

        return ResponseEntity.status(401).body(new TokenResponse("Invalid credentials"));
    }
}
