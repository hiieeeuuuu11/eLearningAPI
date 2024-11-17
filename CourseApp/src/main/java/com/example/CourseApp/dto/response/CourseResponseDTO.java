package com.example.CourseApp.dto.response;

import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.entity.course.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {

        private Course course;

        private List<Chapter> chapterList;

        private Provider provider;

        private ReviewResponseDTO reviewResponseDTO;
}
