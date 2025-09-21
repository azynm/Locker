package com.example.authproj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authproj.dto.RegisterDTO;
import com.example.authproj.service.AuthService;




@RestController
@RequestMapping("api/auth")
public class RegisterController {
    private final AuthService authservice;

    public RegisterController(AuthService authservice) {
        this.authservice = authservice;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        try {
            authservice.registerUser(dto);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering user: " + e.getMessage());
        }
    }
    
    
}
