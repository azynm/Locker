package com.example.authproj.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authproj.entity.Role; // Adjust the package path to where the Role class is

public interface  RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
