package com.example.CourseApp.controller;

import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public List<Course> getAllProviderCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping("/getbyid")
    public ResponseEntity<Course> getProviderCourseById(@RequestParam("id") String id) {
        int id1 = Integer.parseInt(id);
        return ResponseEntity.ok(courseService.getCourseById(id1));
    }


}
