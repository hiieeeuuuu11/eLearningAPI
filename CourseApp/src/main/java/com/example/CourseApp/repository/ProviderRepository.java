package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    List<Provider> findAll();
    Provider findById(int id);
    Provider save(Provider provider);
    void deleteById(int id);
}