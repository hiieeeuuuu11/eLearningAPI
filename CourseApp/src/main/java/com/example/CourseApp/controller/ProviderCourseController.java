package com.example.CourseApp.controller;

import com.example.CourseApp.model.ProviderCourse;
import com.example.CourseApp.service.ProviderCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider-courses")
public class ProviderCourseController {

    private final ProviderCourseService providerCourseService;

    public ProviderCourseController(ProviderCourseService providerCourseService) {
        this.providerCourseService = providerCourseService;
    }

    @GetMapping
    public List<ProviderCourse> getAllProviderCourses() {
        return providerCourseService.getAllProviderCourses();
    }

    @GetMapping("/{id}")
    public ProviderCourse getProviderCourseById(@PathVariable int id) {
        return providerCourseService.getProviderCourseById(id);
    }

    @PostMapping
    public ProviderCourse createProviderCourse(@RequestBody ProviderCourse providerCourse) {
        return providerCourseService.createProviderCourse(providerCourse);
    }

    @PutMapping("/{id}")
    public ProviderCourse updateProviderCourse(@PathVariable int id, @RequestBody ProviderCourse providerCourse) {
        return providerCourseService.updateProviderCourse(id, providerCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteProviderCourse(@PathVariable int id) {
        providerCourseService.deleteProviderCourse(id);
    }


}