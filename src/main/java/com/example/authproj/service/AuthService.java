package com.example.authproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.authproj.config.PassEncoder;
import com.example.authproj.dto.RegisterDTO;
import com.example.authproj.entity.Role;
import com.example.authproj.entity.User;
import com.example.authproj.repositories.RoleRepository;
import com.example.authproj.repositories.UserRepository; // Import the User class


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PassEncoder passEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PassEncoder passEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passEncoder = passEncoder;
    }


    public int checkUserExists(String email) {
        if (userRepository.findByEmail(email) != null) {
            return 1; // User exists
        } else {
            return 0; // User does not exist
        }
    }
    public void registerUser(RegisterDTO registerDTO) {
        if (checkUserExists(registerDTO.getEmail()) == 0) {
            String encryptedPassword = passEncoder.encode().encode(registerDTO.getPassword());
            registerDTO.setPassword(encryptedPassword);
            Role defaultRole = new Role(1, "USER");
            registerDTO.addRole(defaultRole);

            User user = new User();
            user.setUsername(registerDTO.getUsername());
            user.setPassword(registerDTO.getPassword());
            user.setEmail(registerDTO.getEmail());
            user.addRole(defaultRole);
            userRepository.save(user);

           /*
            - todo
                - use rolerepo instead of hardcode
                - will probably need to enter db to do so
            */





        } 
        else {
            throw new IllegalArgumentException("User with this email already exists.");
        }
    }



}
