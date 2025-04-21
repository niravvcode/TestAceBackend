package com.testAce.TestAceBackend.controller;

import com.testAce.TestAceBackend.Entity.Scores;
import com.testAce.TestAceBackend.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping("/addScore")
    public ResponseEntity<?> addScore(@RequestBody Scores score){
        System.out.println(score);
        return scoreService.addScore(score);
    }

    @GetMapping("/getScore")
    public ResponseEntity<?> getScore(){
        return scoreService.getScore();
    }
}
