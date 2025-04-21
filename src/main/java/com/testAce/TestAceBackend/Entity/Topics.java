package com.testAce.TestAceBackend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topics {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;

    private String topicName;
    private String topicDesc;
    private String Logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic",orphanRemoval = true)
    @JsonManagedReference
    private List<SubTopics> subTopics;
}
