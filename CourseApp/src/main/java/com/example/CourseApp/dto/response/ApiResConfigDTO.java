package com.example.CourseApp.dto.response;

import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.entity.course.Provider;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResConfigDTO {

    private boolean status;
    private Integer id;
}
