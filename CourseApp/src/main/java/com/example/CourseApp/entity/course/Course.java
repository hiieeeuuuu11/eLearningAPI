package com.example.CourseApp.entity.course;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    Provider provider;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Topic topic;

    @Column(nullable = false)
    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    String imageUrl;

    Integer isPublished;

    Integer price;

    @Column(nullable = false)
    LocalDateTime createdAt;

    @Column(nullable = false)
    LocalDateTime updatedAt;
}