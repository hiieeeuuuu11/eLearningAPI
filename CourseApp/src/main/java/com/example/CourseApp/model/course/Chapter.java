package com.example.CourseApp.model.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "chapter")
@Entity
@Builder
public class Chapter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    int position;

    String description;

    @ManyToOne(fetch= FetchType.LAZY) @JoinColumn(name = "course_id") @JsonBackReference
    Course course_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id",referencedColumnName = "id")
    @JsonManagedReference
    private List<Lesson> lessons;

}
