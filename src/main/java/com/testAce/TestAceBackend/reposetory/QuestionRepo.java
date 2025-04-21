package com.testAce.TestAceBackend.reposetory;

import com.testAce.TestAceBackend.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Questions, Integer> {
    public List<Questions> findByTopicNameIgnoreCase(String topicName);
}
