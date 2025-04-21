package com.testAce.TestAceBackend.services;

import com.testAce.TestAceBackend.DTOS.LoginResponse;
import com.testAce.TestAceBackend.configuration.CustomeUserDetail;
import com.testAce.TestAceBackend.Entity.Users;
import com.testAce.TestAceBackend.reposetory.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class CustomeUserDetailService implements UserDetailsService {

    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users users = userRepo.findByUserEmail(email);

        if(Objects.isNull(users)){
            throw new UsernameNotFoundException("user not found");
        }
        return new CustomeUserDetail(users);
    }
}
