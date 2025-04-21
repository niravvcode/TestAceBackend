package com.testAce.TestAceBackend.services;

import com.testAce.TestAceBackend.DTOS.AuthLoginRequest;
import com.testAce.TestAceBackend.DTOS.LoginRequest;
import com.testAce.TestAceBackend.DTOS.LoginResponse;
import com.testAce.TestAceBackend.DTOS.RegisterRequest;
import com.testAce.TestAceBackend.Entity.Users;
import com.testAce.TestAceBackend.reposetory.UserRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final AuthenticationManager manager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public boolean saveUser(RegisterRequest registerRequest) {
        Users users = new Users();

        users.setUserName(registerRequest.getUserName());
        users.setUserPassword(passwordEncoder.encode(registerRequest.getUserPassword()));
        users.setUserEmail(registerRequest.getUserEmail());
        users.setUserRole("USER");
        users.setRegistrationDate(new Date());

        Users user =  userRepo.save(users);

        return true;
    }

    public boolean isValid(LoginRequest loginRequest){
        try {
            Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getUserPassword()));
            System.out.println(authenticate.isAuthenticated());
            return authenticate.isAuthenticated();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public Users findByUsername(String userName) {
        return userRepo.findByUserName(userName);
    }

    public Users findByEmail(String userEmail) {
        return userRepo.findByUserEmail(userEmail);
    }

    public ResponseEntity<?> saveGoogleUser(AuthLoginRequest request) {
        Users user = userRepo.findByUserEmail(request.getUserEmail());

        if(user != null){
            LoginResponse response = new LoginResponse(request.getUserName(), request.getUserEmail(), request.getUserPhoto(), jwtService.getToken(request.getUserEmail()),user.getUserRole(),user.getRegistrationDate());
            return ResponseEntity.ok(response);
        }
        Users addUser = new Users();

        addUser.setUserName(request.getUserName());
        addUser.setUserEmail(request.getUserEmail());
        addUser.setUserRole("USER");
        addUser.setUserPhoto(request.getUserPhoto());
        addUser.setUserPassword(String.valueOf(UUID.randomUUID()));
        addUser.setRegistrationDate(new Date());

        try{
            userRepo.save(addUser);
            LoginResponse response = new LoginResponse(addUser.getUserName(), addUser.getUserEmail(), addUser.getUserPhoto(), jwtService.getToken(addUser.getUserEmail()),user.getUserRole(),user.getRegistrationDate());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<?> getAllUser() {
        List<Users> users =  userRepo.findAll();
        return ResponseEntity.ok(users);
    }

}
