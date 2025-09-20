package com.example.authproj.service;

import org.springframework.stereotype.Service;

import com.example.authproj.config.PassEncoder;
import com.example.authproj.dto.RegisterDTO;
import com.example.authproj.entity.Role;
import com.example.authproj.entity.User;
import com.example.authproj.repositories.RoleRepository;
import com.example.authproj.repositories.UserRepository;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PassEncoder passEncoder;


    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PassEncoder passEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passEncoder = passEncoder;
    }


    public boolean checkUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    public void registerUser(RegisterDTO registerDTO) {
        if (!checkUserExists(registerDTO.getEmail())) {
            String encryptedPassword = passEncoder.encode().encode(registerDTO.getPassword());
            Role defaultRole = roleRepository.findByName("USER");


            User user = new User();
            user.setUsername(registerDTO.getUsername());
            user.setPassword(encryptedPassword);
            user.setEmail(registerDTO.getEmail());
            user.addRole(defaultRole);
            userRepository.save(user);






        } 
        else {
            throw new IllegalArgumentException("User with this email already exists.");
        }
    }



}
