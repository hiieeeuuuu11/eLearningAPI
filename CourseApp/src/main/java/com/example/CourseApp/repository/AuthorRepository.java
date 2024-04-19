package com.example.CourseApp.repository;

import com.example.CourseApp.model.course.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // Các phương thức tùy chỉnh có thể được thêm vào đây (nếu cần)
}