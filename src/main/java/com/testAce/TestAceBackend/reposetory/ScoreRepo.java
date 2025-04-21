package com.testAce.TestAceBackend.reposetory;

import com.testAce.TestAceBackend.Entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepo extends JpaRepository<Scores, Integer> {

}
