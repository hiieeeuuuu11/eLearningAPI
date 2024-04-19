package com.example.CourseApp.repository;

import com.example.CourseApp.model.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
}
