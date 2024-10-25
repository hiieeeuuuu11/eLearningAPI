package com.example.CourseApp.controller;

import com.example.CourseApp.entity.course.Lesson;
import com.example.CourseApp.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@Tag(name = "Lesson", description = "APIs for get lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/getbyId")
    @Operation(summary = "Lấy bài học theo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Bài học không tồn tại")
    })
    public ResponseEntity<Lesson> getLessonById(@RequestParam("lesson_id") String id) {
        int id1 = Integer.parseInt(id);
        return ResponseEntity.ok(lessonService.getLessonById(id1));
    }

    @GetMapping("/getbychapter")
    @Operation(summary = "Lấy bài học theo chương")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bài học cho chương")
    })
    public ResponseEntity<List<Lesson>> getLessonByChapter(@RequestParam("lesson_id") String id) {
        int id1 = Integer.parseInt(id);
        return ResponseEntity.ok(lessonService.getLessonByChapter(id1));
    }
}
