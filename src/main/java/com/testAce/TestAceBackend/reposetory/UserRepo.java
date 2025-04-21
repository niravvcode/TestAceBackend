package com.testAce.TestAceBackend.reposetory;

import com.testAce.TestAceBackend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
    public Users findByUserName(String userName);
    public Users findByUserEmail(String userEmail);
}
