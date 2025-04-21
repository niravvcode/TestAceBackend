package com.testAce.TestAceBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TestSubmission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String testTopic;
    private int testId;
    private int score;
    private LocalDateTime submittedAt;
    private String duration;
}
