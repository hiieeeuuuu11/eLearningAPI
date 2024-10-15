package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;

    private final CourseRepository courseRepository;


    public List<Chapter> getallChapter () {
        return  chapterRepository.findAll();
    }

    public List<Chapter> getChapterbyCourse(int course_id){
        return chapterRepository.findChaptersByCourse_Id(course_id);
    }

    public int getNumberOfChapter(int course_id) {
        return chapterRepository.countByCourse_Id(course_id);
    }

    public Chapter getChapterbyId(int id){
        return chapterRepository.findById(id).orElse(null);
    }

}
