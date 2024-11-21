package com.example.CourseApp.dto.response;

import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.entity.course.Lesson;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterResponse {

  Chapter chapter;
  List<Lesson> lessonList;

}
