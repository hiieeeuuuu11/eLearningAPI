package com.example.CourseApp.service;

import com.amazonaws.HttpMethod;
import com.example.CourseApp.entity.course.Lesson;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.example.CourseApp.repository.LessonRepository;
import com.example.CourseApp.service.cloud.S3Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final ChapterRepository chapterRepository;

    private final S3Service s3service;

    public Map<String, String> getPresignUrlFromS3(String type) {
        Map<String, String> response = new HashMap<>();
        String fileName = type + "/" + System.currentTimeMillis();
        String url = s3service.generatePreSignedUrl(fileName, HttpMethod.PUT);
        response.put("url", url);
        response.put("fileName", fileName);
        return response;
    }

    public List<Lesson> getallLesson() {
        return lessonRepository.findAll().stream().peek(item->{
            if (item.getVideoUrl() != null) {
                String videoUrl = s3service.generatePreSignedUrl(item.getVideoUrl(), HttpMethod.GET);
                item.setVideoUrl(videoUrl);
            }
            if (item.getTextUrl() != null) {
                String textUrl = s3service.generatePreSignedUrl(item.getTextUrl(), HttpMethod.GET);
                item.setTextUrl(textUrl);
            }
        }).toList();
    }

    public List<Lesson> getLessonByChapterId(int chapter_id) {
        return lessonRepository.findLessonsByChapter_Id(chapter_id).stream().peek(item->{
            if (item.getVideoUrl() != null) {
                String videoUrl = s3service.generatePreSignedUrl(item.getVideoUrl(), HttpMethod.GET);
                item.setVideoUrl(videoUrl);
            }
            if (item.getTextUrl() != null) {
                String textUrl = s3service.generatePreSignedUrl(item.getTextUrl(), HttpMethod.GET);
                item.setTextUrl(textUrl);
            }
        }).toList();
    }

    public Lesson getLessonbyId(int lesson_id) {
        if (!lessonRepository.existsById(lesson_id)) {
            throw new RuntimeException("Lesson not found");
        }
        Lesson lesson = lessonRepository.findById(lesson_id).orElse(null);
        if (lesson.getVideoUrl() != null) {
            String videoUrl = s3service.generatePreSignedUrl(lesson.getVideoUrl(), HttpMethod.GET);
            lesson.setVideoUrl(videoUrl);
        }
        if (lesson.getTextUrl() != null) {
            String textUrl = s3service.generatePreSignedUrl(lesson.getTextUrl(), HttpMethod.GET);
            lesson.setTextUrl(textUrl);
        }
        return lesson;
    }


}
