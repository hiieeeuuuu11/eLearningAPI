package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Enrollment;
import com.example.CourseApp.entity.course.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewByEnrollmentId(int enrollment_id);
    boolean existsByEnrollmentId(int enrollment_id);

    @Query("SELECT r FROM Review r " +
            "JOIN Enrollment e ON r.enrollment.id = e.id " +
            "WHERE e.course.id = :courseId AND e.learner.id = :studentId")
    Optional<Review> findReviewByCourseIdAndStudentId(@Param("courseId") int courseId,
                                                      @Param("studentId") int studentId);

    @Query("SELECT COUNT(r) > 0 FROM Review r " +
            "JOIN Enrollment e ON r.enrollment.id = e.id " +
            "WHERE e.course.id = :courseId AND e.learner.id = :studentId")
    boolean existsByCourseIdAndStudentId(@Param("courseId") int courseId,
                                         @Param("studentId") int studentId);
}