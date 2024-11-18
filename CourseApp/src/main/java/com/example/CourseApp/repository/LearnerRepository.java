package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.entity.course.Enrollment;
import com.example.CourseApp.entity.course.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearnerRepository extends JpaRepository<Learner,Integer> {

    Learner findByUserId(int userID);

    boolean existsByUserId(int userID);

}