package com.example.CourseApp.service;

import com.example.CourseApp.model.ProviderCourse;
import com.example.CourseApp.model.course.Author;
import com.example.CourseApp.model.course.Course;
import com.example.CourseApp.repository.AuthorRepository;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthorRepository authorRepository;

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int course_id) {
        Course course = courseRepository.findById(course_id).orElse(null);
        return course;
    }

    public Course getCourse(String token) throws JsonProcessingException {
        String url =  "http://"+"localhost:8080"+"/tpa/getcoursebyid?id=11";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String response = responseEntity.getBody();
            Course course = objectMapper.readValue(response, Course.class);
            return course;
        } else {
            return null;
        }
    }

    public Course addCourse(Course course,String token){
        course.setToken(token);
        return courseRepository.save(course);

    }

}
