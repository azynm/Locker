package com.example.authproj.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authproj.entity.Role; 

public interface  RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
