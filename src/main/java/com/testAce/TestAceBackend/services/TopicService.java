package com.testAce.TestAceBackend.services;

import com.testAce.TestAceBackend.Entity.Questions;
import com.testAce.TestAceBackend.Entity.SubTopics;
import com.testAce.TestAceBackend.Entity.Topics;
import com.testAce.TestAceBackend.reposetory.TopicRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService{

    private final TopicRepo topicRepo;

    public Topics saveTopic(Topics topic) {
        if (topic.getSubTopics() != null) {
            for (SubTopics subTopic : topic.getSubTopics()) {
                subTopic.setTopic(topic); // ✅ Set parent topic

                if (subTopic.getQuestions() != null) {
                    for (Questions question : subTopic.getQuestions()) {
                        question.setSubTopic(subTopic);// ✅ Set parent subtopic
                        question.setTopicName(topic.getTopicName());
                    }
                }
            }
        }
        return topicRepo.save(topic);
    }

    public List<Topics> getAllTopic() {
        return topicRepo.findAll();
    }
}
