package com.example.CourseApp.model;

import com.example.CourseApp.model.course.Chapter;
import com.example.CourseApp.model.course.Course;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "provider")
@Entity
@Builder
public class ProviderCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id",referencedColumnName = "id")
    @JsonManagedReference
    private List<Course> courses;

}
