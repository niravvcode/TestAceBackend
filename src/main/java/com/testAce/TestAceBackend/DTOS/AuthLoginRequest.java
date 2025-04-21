package com.testAce.TestAceBackend.DTOS;

import lombok.*;

@Data
public class AuthLoginRequest {
    private String userEmail;
    private String userName;
    private String userPhoto;
}
