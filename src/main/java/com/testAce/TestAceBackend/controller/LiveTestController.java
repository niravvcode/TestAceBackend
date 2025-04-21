package com.testAce.TestAceBackend.controller;
import com.testAce.TestAceBackend.Entity.LiveTest;
import com.testAce.TestAceBackend.Entity.TestSubmission;
import com.testAce.TestAceBackend.reposetory.TestSubmissionRepo;
import com.testAce.TestAceBackend.services.LiveTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class LiveTestController {

    @Autowired
    private LiveTestService testService;


    private final TestSubmissionRepo submissionRepo;

    // 1. Add new test
    @PostMapping("/add")
    public ResponseEntity<LiveTest> addTest(@RequestBody LiveTest test) {
        LiveTest savedTest = testService.addTest(test);
        return ResponseEntity.ok(savedTest);
    }

    // 2. Get all tests
    @GetMapping("/getAll")
    public ResponseEntity<List<LiveTest>> getAllTests() {
        List<LiveTest> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    // 3. Get test by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTestById(@PathVariable int id) {
        Optional<LiveTest> test = testService.getTestById(id);
        return test.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    // 4. Delete test by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTestById(@PathVariable int id) {
        String message = testService.deleteTestById(id);
        return ResponseEntity.ok(message);
    }

    // 5. Get currently active (live) tests
    @GetMapping("/active")
    public ResponseEntity<List<LiveTest>> getActiveTests() {
        List<LiveTest> activeTests = testService.getAvailableTests();
        return ResponseEntity.ok(activeTests);
    }

    @PostMapping("/submitTest")
    public ResponseEntity<?> submitTest(@RequestBody TestSubmission testSubmission){
        System.out.println("in test controller");
        TestSubmission submission = submissionRepo.save(testSubmission);

        return ResponseEntity.ok(submission);
    }

    @GetMapping("/getAllTest")
    public ResponseEntity<List<TestSubmission>> getAllSubmittedTests() {
        List<TestSubmission> tests = submissionRepo.findAll();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/getResultByName/{id}")
    public ResponseEntity<List<TestSubmission>> getResultByName(@PathVariable int id) {
        System.out.println("in leaderboard component");
        System.out.println(id);
        List<TestSubmission> tests = submissionRepo.findAllByTestId(id);
        System.out.println(tests);
        return ResponseEntity.ok(tests);
    }
}