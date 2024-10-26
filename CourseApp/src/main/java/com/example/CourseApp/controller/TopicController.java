package com.example.CourseApp.controller;


import com.example.CourseApp.dto.response.BaseResponse;
import com.example.CourseApp.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/topic")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping()
    public ResponseEntity<?> getTopics() {
        return ResponseEntity.ok(BaseResponse.builder().data(topicService.getTopics()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable("id") int id) {
        return ResponseEntity.ok(BaseResponse.builder().data(topicService.getTopicById(id)).build());
    }

}
