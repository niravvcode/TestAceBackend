package com.testAce.TestAceBackend.DTOS;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
public class RegisterRequest {

    @NotEmpty(message = "Username is required")
    @Size(min = 5, max = 12, message = "Username must be between 5 to 12 character")
    private String userName;

    @NotEmpty(message = "Password is required")
    @Size(min = 5, max = 12, message = "Password must be between 5 to 12 character")
    private String userPassword;

    @Email
    @NotEmpty
    private String userEmail;
}
