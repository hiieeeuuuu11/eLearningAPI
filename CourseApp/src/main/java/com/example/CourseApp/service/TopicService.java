package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Topic;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.TopicRepository;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> getTopics() {
        List<Topic> topics = topicRepository.findAll();
        if (topics.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_TOPIC_FOUND);
        }
        return topics;
    }

    public Topic getTopicById(int id) {
        return topicRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.TOPIC_NOT_FOUND));
    }

}
