package com.testAce.TestAceBackend.controller;

import com.testAce.TestAceBackend.DTOS.LoginRequest;
import com.testAce.TestAceBackend.DTOS.LoginResponse;
import com.testAce.TestAceBackend.DTOS.RegisterRequest;
import com.testAce.TestAceBackend.Entity.Users;
import com.testAce.TestAceBackend.reposetory.UserRepo;
import com.testAce.TestAceBackend.services.JwtService;
import com.testAce.TestAceBackend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    JwtService jwtService;
    UserService userService;
    AuthenticationManager authenticationManager;
    UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult){
        System.out.println(registerRequest);
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            System.out.println(errors);
            return ResponseEntity.badRequest().body(errors);
        }

        Users isUsernameExist = userService.findByUsername(registerRequest.getUserName());

        if(isUsernameExist != null){
            return ResponseEntity.badRequest().body(Collections.singletonMap("nameError", "name is already taken"));
        }

        Users isEmailExist= userService.findByEmail(registerRequest.getUserEmail());

        if(isEmailExist != null){
            System.out.println(isEmailExist.getUserEmail());
            return ResponseEntity.badRequest().body(Collections.singletonMap("emailError", "email is already exist"));
        }

        userService.saveUser(registerRequest);

        return ResponseEntity.ok(Collections.singletonMap("message", "Register successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest,BindingResult bindingResult){
//        System.out.println(loginRequest.getUserEmail() + " " + loginRequest.getUserPassword());

            if (userService.isValid(loginRequest)) {
                System.out.println("user is valid");
                Users user = userRepo.findByUserEmail(loginRequest.getUserEmail());
                String username = user.getUserName();
                LoginResponse loginResponse = new LoginResponse(username, loginRequest.getUserEmail(),user.getUserPhoto(), jwtService.getToken(user.getUserEmail()),user.getUserRole(), user.getRegistrationDate());
                System.out.println(loginResponse);
                return ResponseEntity.ok(loginResponse);
            }
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "incorrect username or password"));

    }
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getUsers(){
        return  userService.getAllUser();

    }
}

