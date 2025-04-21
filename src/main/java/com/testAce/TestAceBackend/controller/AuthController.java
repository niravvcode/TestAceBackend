package com.testAce.TestAceBackend.controller;

import com.testAce.TestAceBackend.DTOS.AuthLoginRequest;
import com.testAce.TestAceBackend.Entity.Users;
import com.testAce.TestAceBackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/auth")
class AuthController{

    private final UserService userService;

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody AuthLoginRequest request){

        System.out.println(request);

       if(request.getUserEmail() != null && request.getUserName() != null ){
          return userService.saveGoogleUser(request);
       }

       return ResponseEntity.badRequest().body("Invalid request payload");
    }


}