package com.testAce.TestAceBackend.DTOS;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty(message = "password is required")
    private String userPassword;

    @NotEmpty(message = "email is required")
    private String userEmail;
}
