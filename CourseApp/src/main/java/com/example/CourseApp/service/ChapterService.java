package com.example.CourseApp.service;

import com.example.CourseApp.dto.response.ChapterResponse;
import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.exceptions.BadRequestException;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.example.CourseApp.repository.LessonRepository;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public List<Chapter> getAllChapter() {
        List<Chapter> chapters = chapterRepository.findAll();
        if (chapters.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_CHAPTER_FOUND);
        }
        return chapters;
    }

    public List<Chapter> getChapterByCourse(int course_id) {
        if (courseRepository.findById(course_id).isEmpty()) {
            throw new BadRequestException("Course không tồn tại với ID: " + course_id);
        }
        List<Chapter> chapters = chapterRepository.findChaptersByCourse_Id(course_id);
        if (chapters.isEmpty()) {
            throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_CHAPTER_FOUND_FOR_COURSE);
        }
        return chapters;
    }

    public int getNumberOfChapter(int course_id) {
        if (courseRepository.findById(course_id).isEmpty()) {
            throw new BadRequestException("Course không tồn tại với ID: " + course_id);
        }
        return chapterRepository.countByCourse_Id(course_id);
    }

    public ChapterResponse getChapterById(int id) {
        return chapterRepository.findById(id)
            .map(chapter1 -> {
                return ChapterResponse.builder()
                    .chapter(chapter1)
                    .lessonList(lessonRepository.findLessonsByChapter_Id(chapter1.getId()))
                    .build();
            })
                .orElseThrow(() ->
                        new ObjectNotFoundException(ResponseStatusCodeConst.CHAPTER_NOT_FOUND));


    }
}
