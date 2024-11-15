package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Enrollment;
import com.example.CourseApp.entity.course.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewByEnrollmentId(int enrollment_id);

}