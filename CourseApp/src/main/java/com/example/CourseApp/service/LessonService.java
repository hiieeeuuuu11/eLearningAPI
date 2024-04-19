package com.example.CourseApp.service;

import com.example.CourseApp.model.course.Course;
import com.example.CourseApp.model.course.Lesson;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.example.CourseApp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;

    public String getLessonUrl(int id){
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        return lesson.getLessonApi();
    }

    public String getToken(int id){
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        return chapterRepository.getReferenceById(lesson.getChapter_id().getId()).getCourse_id().getToken();
    }

}
