package com.testAce.TestAceBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class SubTopics {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subTopicId;

    private String subTopicName;

    private String subTopicVidioLink;

    @ManyToOne
    @JoinColumn(name = "topicId", nullable = false)
    @JsonBackReference
    private Topics topic;

    @OneToMany(mappedBy = "subTopic", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Questions> questions;

}
