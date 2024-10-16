package com.example.CourseApp.entity.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "providers")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String description;

   // String email;

    @OneToMany
    @JoinColumn(name = "author",referencedColumnName = "id")
    @JsonManagedReference("reference1")
    @JsonIgnore
    List<Course> course;



}
