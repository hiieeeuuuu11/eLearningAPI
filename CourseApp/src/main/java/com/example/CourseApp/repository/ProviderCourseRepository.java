package com.example.CourseApp.repository;

import com.example.CourseApp.model.ProviderCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderCourseRepository extends JpaRepository<ProviderCourse, Integer> {
}