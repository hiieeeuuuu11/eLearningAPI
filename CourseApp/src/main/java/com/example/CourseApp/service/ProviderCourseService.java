package com.example.CourseApp.service;

import com.example.CourseApp.model.ProviderCourse;
import com.example.CourseApp.repository.ProviderCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderCourseService {

    private final ProviderCourseRepository providerCourseRepository;

    public ProviderCourseService(ProviderCourseRepository providerCourseRepository) {
        this.providerCourseRepository = providerCourseRepository;
    }

    public List<ProviderCourse> getAllProviderCourses() {
        return providerCourseRepository.findAll();
    }

    public ProviderCourse getProviderCourseById(int id) {
        return providerCourseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider course not found"));
    }

    public ProviderCourse createProviderCourse(ProviderCourse providerCourse) {
        return providerCourseRepository.save(providerCourse);
    }

    public ProviderCourse updateProviderCourse(int id, ProviderCourse updatedProviderCourse) {
        ProviderCourse existingProviderCourse = getProviderCourseById(id);
        existingProviderCourse.setName(updatedProviderCourse.getName());
        existingProviderCourse.setAddress(updatedProviderCourse.getAddress());
        existingProviderCourse.setCourses(updatedProviderCourse.getCourses());
        return providerCourseRepository.save(existingProviderCourse);
    }

    public void deleteProviderCourse(int id) {
        ProviderCourse providerCourse = getProviderCourseById(id);
        providerCourseRepository.delete(providerCourse);
    }
}