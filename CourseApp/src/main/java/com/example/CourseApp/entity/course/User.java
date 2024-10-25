package com.example.CourseApp.entity.course;

import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Column(unique = true, nullable = false)
    String email;
    
    @Column(name = "password_hash", nullable = false)
    String passwordHash;
    
    @Column(name = "first_name")
    String firstName;
    
    @Column(name = "last_name")
    String lastName;
    
    @Column(name = "created_at")
    LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}