package com.testAce.TestAceBackend.services;

import com.testAce.TestAceBackend.Entity.Scores;
import com.testAce.TestAceBackend.reposetory.ScoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepo scoreRepo;

    public ResponseEntity<?> addScore(Scores score) {
        Scores scores = scoreRepo.save(score);
        if(scores != null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> getScore() {
        List<Scores> scores =  scoreRepo.findAll();
        return ResponseEntity.ok(scores);
    }
}
