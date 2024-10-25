package com.example.CourseApp.service;
import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.repository.ProviderRepository;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    CourseRepository courseRepository;
    ChapterRepository chapterRepository;
    ObjectMapper objectMapper;
    ProviderRepository providerRepository;

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int course_id) {
        Course course = courseRepository.findById(course_id).orElse(null);
        return course;
    }

    public List<Course> getCourseByProvider(int provider_id) {
        return courseRepository.findCoursesByProviderId(provider_id);
    }

    public List<Course> getCourseByTopic(int topic_id) {
        return courseRepository.findCoursesByTopicId(topic_id);
    }

}
