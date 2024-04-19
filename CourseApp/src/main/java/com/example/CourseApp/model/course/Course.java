package com.example.CourseApp.model.course;

import com.example.CourseApp.model.ProviderCourse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "course")
@Entity
@Builder
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String description;

    String imageUrl;

    String topic;

    String token;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    @JsonManagedReference
    private List<Chapter> chapters;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "author")
    @JsonBackReference("reference1")
    Author author;

    @ManyToOne @JoinColumn(name = "provider_id") @JsonBackReference()
    ProviderCourse providerCourse;

}