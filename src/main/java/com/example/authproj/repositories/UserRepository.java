package com.example.authproj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authproj.entity.User; 

public interface  UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);

}
