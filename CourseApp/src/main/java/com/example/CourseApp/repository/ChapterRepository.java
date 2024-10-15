package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {

    List<Chapter> findChaptersByCourse_Id(int course_id);

    Integer countByCourse_Id(int course_id);

}
