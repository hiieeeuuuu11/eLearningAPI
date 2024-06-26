package com.example.CourseApp.controller;

import com.example.CourseApp.dto.ApiUrlRequest;
import com.example.CourseApp.model.ProviderCourse;
import com.example.CourseApp.model.course.Course;
import com.example.CourseApp.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @GetMapping("getcourse")
    public Course getCourses(@RequestHeader("token") String token,@RequestBody ApiUrlRequest url) throws JsonProcessingException {

        return courseService.addCourse(courseService.getCourse(token,url.courseApi()),token,url);
    }

}
