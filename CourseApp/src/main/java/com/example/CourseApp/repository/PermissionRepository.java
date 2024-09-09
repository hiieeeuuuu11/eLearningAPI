package com.example.CourseApp.repository;

import com.example.CourseApp.model.course.Lesson;
import com.example.CourseApp.model.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
