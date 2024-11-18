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
    
    @Column(name = "is_deleted", nullable = false)
    boolean is_deleted;
    
    @Column(name = "is_verified")
    boolean is_verified;
    
    @Column(name = "password")
    String password;
    
    @Column(name = "created_at")
    LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "phone_number")
    String phone_number;

    @Column(name = "reset_password_code")
    String reset_password_code;

    @Column(name = "role")
    String role;

    @Column(name = "username")
    String username;

    @Column(name = "verification_code")
    String verification_code;

}