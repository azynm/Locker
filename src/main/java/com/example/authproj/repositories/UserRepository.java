package com.example.authproj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authproj.entity.User; // Adjust the package path to where the User class is located

public interface  UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
