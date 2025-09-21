package com.example.authproj.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {
    @NotNull
    String password;
    @NotNull
    String email;

    public LoginDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}