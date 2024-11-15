package com.example.CourseApp.dto.response;

import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.entity.course.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    int rating;
    String comment;
}
