package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Provider findByUserId(int userID);

    boolean existsByUserId(int userID);

}