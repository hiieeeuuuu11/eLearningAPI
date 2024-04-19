package com.example.CourseApp.service;

import com.example.CourseApp.model.course.Chapter;
import com.example.CourseApp.model.course.Course;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChapterService {

    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    CourseRepository courseRepository;


    public List<Chapter> getallChapter () {
        return  chapterRepository.findAll();
    }

    public List<Chapter> getChapterbyCourse(int course_id){
        Course course  = courseRepository.findById(course_id).orElse(null);
        return course.getChapters();
    }

    public int getNumberOfChapter(int course_id) {
        return courseRepository.getNumberOfChapter(course_id);
    }

    public Chapter getChapterbyId(int id){
        Chapter chapter  = chapterRepository.findById(id).orElse(null);
        return chapter;
    }

}
