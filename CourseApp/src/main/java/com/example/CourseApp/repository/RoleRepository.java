package com.example.CourseApp.repository;

import com.example.CourseApp.model.course.Chapter;
import com.example.CourseApp.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {


   // List<Chapter> getChaptersByCourse_idOrderByPosition(int chapter_id);

}
