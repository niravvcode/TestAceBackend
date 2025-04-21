package com.testAce.TestAceBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Questions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String questionText;

    private String optionD;
    private String optionA;
    private String optionB;
    private String optionC;

    private String correctAnswer;
    private String difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "subTopicId", nullable = false)
    @JsonBackReference
    private SubTopics subTopic;

    @Column(name = "topic_name")
    private String   topicName;
}
