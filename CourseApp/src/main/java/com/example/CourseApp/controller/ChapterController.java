package com.example.CourseApp.controller;

import com.example.CourseApp.model.course.Chapter;
import com.example.CourseApp.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping
    public List<Chapter> getAllProviderChapters() {
        return chapterService.getallChapter();
    }

    @GetMapping("/getbyid")
    public ResponseEntity<Chapter> getChapterById(@RequestParam("id") int id) {
        return ResponseEntity.ok(chapterService.getChapterbyId(id));
    }

    @GetMapping("/getbycourse")
    public ResponseEntity<List<Chapter>> getChapterByCourse(@RequestParam("course-id") int id) {
        return ResponseEntity.ok(chapterService.getChapterbyCourse(id));
    }



}
