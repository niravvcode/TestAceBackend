package com.testAce.TestAceBackend.reposetory;

import com.testAce.TestAceBackend.Entity.LiveTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveTestRepo extends JpaRepository<LiveTest, Integer> {
}
