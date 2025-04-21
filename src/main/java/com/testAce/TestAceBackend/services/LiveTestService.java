package com.testAce.TestAceBackend.services;

import com.testAce.TestAceBackend.Entity.LiveTest;
import com.testAce.TestAceBackend.reposetory.LiveTestRepo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LiveTestService {

    @Autowired
    private LiveTestRepo testRepository;

    public LiveTest addTest(LiveTest test) {
        return testRepository.save(test);
    }

    public List<LiveTest> getAllTests() {
        return testRepository.findAll();
    }

    public Optional<LiveTest> getTestById(int id) {
        return testRepository.findById(id);
    }

    public String deleteTestById(int id) {
        Optional<LiveTest> optional = testRepository.findById(id);
        if (optional.isPresent()) {
            testRepository.deleteById(id);
            return "Test with ID " + id + " deleted successfully.";
        } else {
            return "Test not found with ID: " + id;
        }
    }

    public List<LiveTest> getAvailableTests() {
        LocalDateTime now = LocalDateTime.now();
        return testRepository.findAll().stream()
                .filter(test -> test.getStartTime().isBefore(now) && test.getEndTime().isAfter(now))
                .collect(Collectors.toList());
    }
}

