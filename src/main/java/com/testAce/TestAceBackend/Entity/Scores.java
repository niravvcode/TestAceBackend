package com.testAce.TestAceBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scoreId;
    private String userName;
    private String topicName;
    private int score;
    private Date attemptedDate;
    private String duration;

}
