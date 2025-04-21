package com.testAce.TestAceBackend.controller;
import com.testAce.TestAceBackend.Entity.Questions;
import com.testAce.TestAceBackend.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/getQuestion")
    public ResponseEntity<?> getQuestion(@RequestParam String topicName, @RequestParam int noOfQuestions){
        System.out.println("in question controller");
        return questionService.getAllQuestion(topicName,noOfQuestions);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody List<Questions> questions){
        return questionService.addQuestion(questions);
    }

    @PostMapping("/addOneQuestion")
    public ResponseEntity<?> addOneQuestion(@RequestBody Questions questions){
        return questionService.addOneQuestion(questions);
    }
}
