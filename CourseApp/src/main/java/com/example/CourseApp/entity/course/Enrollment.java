package com.example.CourseApp.entity.course;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enrollments")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Learner learner;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    @Column(nullable = false)
    LocalDateTime enrollment_date;

}

