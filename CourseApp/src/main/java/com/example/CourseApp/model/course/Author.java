package com.example.CourseApp.model.course;

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
@Table(name = "author")
@Entity
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String description;

    String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author",referencedColumnName = "id")
    @JsonManagedReference("reference1")
    List<Course> course;

}
