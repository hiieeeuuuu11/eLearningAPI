package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
    List<Lesson> findLessonsByChapter_Id(int chapter_id);

    Lesson findLessonById(int id);
}
