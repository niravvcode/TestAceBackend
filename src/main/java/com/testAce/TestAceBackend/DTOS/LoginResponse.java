package com.testAce.TestAceBackend.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String name;
    private String email;
    private String photo;
    private String jwtToken;
    private String role;
    private Date registrationDate;
}
