package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.exceptions.BadRequestException;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.ProviderRepository;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final ObjectMapper objectMapper;
    private final ProviderRepository providerRepository;

    public List<Course> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND);
        }
        return courses;
    }

    public Course getCourseById(int course_id) {
        return courseRepository.findById(course_id)
                .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.COURSE_NOT_FOUND));
    }

    public List<Course> getCourseByProvider(int provider_id) {
        if (providerRepository.findById(provider_id).isEmpty()) {
            throw new BadRequestException("Provider không tồn tại với ID: " + provider_id);
        }
        List<Course> courses = courseRepository.findCoursesByProviderId(provider_id);
        if (courses.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND_FOR_PROVIDER);
        }
        return courses;
    }

    public List<Course> getCourseByTopic(int topic_id) {
        List<Course> courses = courseRepository.findCoursesByTopicId(topic_id);
        if (courses.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND_FOR_TOPIC);
        }
        return courses;
    }
}
