package com.example.CourseApp.repository;

import com.example.CourseApp.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query(value = "SELECT COUNT(id) FROM chapter WHERE course_id = :course_id",nativeQuery = true)
    int getNumberOfChapter(int course_id);

}