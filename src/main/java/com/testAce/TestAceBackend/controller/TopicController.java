package com.testAce.TestAceBackend.controller;

import com.testAce.TestAceBackend.Entity.Topics;
import com.testAce.TestAceBackend.reposetory.TopicRepo;
import com.testAce.TestAceBackend.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor

public class TopicController {

    private final TopicService topicService;

    @PostMapping("/add")
    public ResponseEntity<?> addTopic(@RequestBody Topics topics){

        Topics topic = topicService.saveTopic(topics);

        if(topic != null)
            return ResponseEntity.ok("Topic saved successfully");
        return ResponseEntity.badRequest().body("something went wrong");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTopic(){
        System.out.println("request coming");
        List<Topics> topics =  topicService.getAllTopic();

        if(topics != null)
            return ResponseEntity.ok(topics);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
