package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findCoursesByProviderId(int provider_id);

    List<Course> findCoursesByTopicId(int topic_id);

}