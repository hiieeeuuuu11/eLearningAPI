package com.example.CourseApp.controller;

import com.example.CourseApp.model.ProviderCourse;
import com.example.CourseApp.model.course.Course;
import com.example.CourseApp.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    CourseService courseService;

    @GetMapping
    public Course getCourses() throws JsonProcessingException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ0cGEiOjYsImNvdXJzZSI6MTEsImlhdCI6MTcxMzQ4NDgwMCwiZXhwIjoxNzEzNTcxMjAwfQ.5iBit5NbZD7nA3NKeZPhEYxzpl1BgLA0pYws8nARtfg";
        return courseService.addCourse(courseService.getCourse(token),token);
    }

}
