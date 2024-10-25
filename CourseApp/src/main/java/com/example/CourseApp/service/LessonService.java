package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Lesson;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.example.CourseApp.repository.LessonRepository;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;

    public Lesson getLessonById(int lesson_id) {
        return lessonRepository.findLessonById(lesson_id)
                .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.LESSON_NOT_FOUND));
    }

    public List<Lesson> getLessonByChapter(int chapter_id) {
        List<Lesson> lessons = lessonRepository.findLessonsByChapter_Id(chapter_id);
        if (lessons.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_LESSON_FOUND_FOR_CHAPTER);
        }
        return lessons;
    }
}
