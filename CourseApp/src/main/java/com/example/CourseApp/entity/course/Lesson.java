package com.example.CourseApp.entity.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    Integer position;

    String videoUrl;

    String textUrl;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    @JsonIgnore
    Chapter chapter;

}
