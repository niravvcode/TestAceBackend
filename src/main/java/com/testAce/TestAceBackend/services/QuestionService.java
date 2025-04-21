package com.testAce.TestAceBackend.services;

import com.testAce.TestAceBackend.Entity.Questions;
import com.testAce.TestAceBackend.reposetory.QuestionRepo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    public ResponseEntity<?> getAllQuestion(String topicName,int noOfQuestions) {
        topicName = topicName.trim();
        System.out.println("Received topicName: [" + topicName + "]");

        List<Questions> questions = questionRepo.findByTopicNameIgnoreCase(topicName);
        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions found for: [" + topicName + "]");
            return ResponseEntity.internalServerError().body("No questions found");
        }
        long seed = generateSeed(topicName, noOfQuestions);

        // Shuffle the list using a consistent seed
        Collections.shuffle(questions, new Random(seed));
        // Shuffle the list of questions to randomize the order


        // If there are more questions than needed, limit to 'noOfQuestions'
        if (questions.size() > noOfQuestions) {
            questions = questions.subList(0, noOfQuestions);
        }
        System.out.println(questions);
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<?> addQuestion(List<Questions> questions) {
        List<Questions> savedQuestions = questionRepo.saveAll(questions);
        return ResponseEntity.ok(savedQuestions);
    }
    private long generateSeed(String topicName, int noOfQuestions) {
        // Generate a seed using a combination of topicName and noOfQuestions to make it consistent for the same inputs
        return (topicName + noOfQuestions).hashCode();
    }

    public ResponseEntity<?> addOneQuestion(Questions questions) {
        Questions que = questionRepo.save(questions);

            return ResponseEntity.ok("");


    }
}
