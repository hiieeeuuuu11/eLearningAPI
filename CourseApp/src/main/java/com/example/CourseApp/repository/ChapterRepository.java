package com.example.CourseApp.repository;

import com.example.CourseApp.model.course.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {


   // List<Chapter> getChaptersByCourse_idOrderByPosition(int chapter_id);

}
