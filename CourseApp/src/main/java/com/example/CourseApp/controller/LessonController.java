package com.example.CourseApp.controller;

import com.example.CourseApp.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lesson")
@CrossOrigin("*")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping("/getlessonurl")
    public ResponseEntity<Map<String,String>> getLessonUrl(@RequestParam("id") int id){
        Map<String,String> response = new HashMap<>();
        response.put("url",lessonService.getLessonUrl(id));
        response.put("token",lessonService.getToken(id));
        return ResponseEntity.ok(response);
    }

}
