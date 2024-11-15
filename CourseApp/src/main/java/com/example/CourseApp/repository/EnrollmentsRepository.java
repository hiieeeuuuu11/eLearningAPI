package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.entity.course.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentsRepository extends JpaRepository<Enrollment,Integer> {
    Enrollment findEnrollmentByCourse_Id(int course_id);

}