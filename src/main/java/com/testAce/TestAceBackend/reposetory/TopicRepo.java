package com.testAce.TestAceBackend.reposetory;

import com.testAce.TestAceBackend.Entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topics, Integer> {
}
