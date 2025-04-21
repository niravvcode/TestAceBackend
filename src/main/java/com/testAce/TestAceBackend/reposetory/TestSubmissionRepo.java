package com.testAce.TestAceBackend.reposetory;

import com.testAce.TestAceBackend.Entity.TestSubmission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestSubmissionRepo extends JpaRepository<TestSubmission, Integer> {
    List<TestSubmission> findByTestTopic(String testTopic);

    List<TestSubmission> findAllById(int id);

    List<TestSubmission> findAllByTestTopic(String topicName);

    List<TestSubmission> findAllByTestId(int id);

    List<TestSubmission> findByTestId(int id);

    List<TestSubmission> findBytestId(int id);
}
