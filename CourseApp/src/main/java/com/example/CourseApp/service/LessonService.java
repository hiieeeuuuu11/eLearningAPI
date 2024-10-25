package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Lesson;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.example.CourseApp.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    LessonRepository lessonRepository;
    CourseRepository courseRepository;
    ChapterRepository chapterRepository;

    public Lesson getLessonById(int lesson_id) {
        return lessonRepository.findLessonById(lesson_id);
    }

    public List<Lesson> getLessonByChapter(int chapter_id) {
        return lessonRepository.findLessonsByChapter_Id(chapter_id);
    }
}
