package com.example.CourseApp.service;

import com.example.CourseApp.dto.response.CourseResponseDTO;
import com.example.CourseApp.dto.response.ReviewResponseDTO;
import com.example.CourseApp.entity.course.*;
import com.example.CourseApp.exceptions.BadRequestException;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.*;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;
  private final ChapterRepository chapterRepository;
  private final ProviderRepository providerRepository;
  private final EnrollmentsRepository enrollmentsRepository;
  private final ReviewRepository reviewRepository;
  public List<CourseResponseDTO> getAllCourse() {
    // Retrieve all courses
    List<Course> courses = courseRepository.findAll();

    if (courses.isEmpty()) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND);
    }

    return courses.stream().map(course -> {
      List<Chapter> listChapter = chapterRepository.findChaptersByCourse_Id(course.getId());
      Provider provider = providerRepository.findById(course.getProvider().getId())
              .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND));
      Enrollment enrollment = enrollmentsRepository.findEnrollmentByCourse_Id(course.getId());
      Review review = reviewRepository.findReviewByEnrollmentId(enrollment.getId());
      ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(review.getRating(),review.getComment());
      return new CourseResponseDTO(course, listChapter, provider,reviewResponseDTO);
    }).collect(Collectors.toList());
  }

  public CourseResponseDTO getCourseById(int course_id) {
    Course course= courseRepository
            .findById(course_id)
            .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.COURSE_NOT_FOUND));
    List<Chapter> listChapter = chapterRepository.findChaptersByCourse_Id(course_id);
    Provider provider = providerRepository.findById(course.getProvider().getId()).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND));
    Enrollment enrollment = enrollmentsRepository.findEnrollmentByCourse_Id(course_id);
    Review review = reviewRepository.findReviewByEnrollmentId(enrollment.getId());
    ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(review.getRating(),review.getComment());

    return new CourseResponseDTO(course,listChapter,provider,reviewResponseDTO);
  }

  public List<CourseResponseDTO> getCourseByProvider(int providerId) {
    if (!providerRepository.existsById(providerId)) {
      throw new BadRequestException("Provider không tồn tại với ID: " + providerId);
    }

    List<Course> courses = courseRepository.findCoursesByProviderId(providerId);

    if (courses.isEmpty()) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND_FOR_PROVIDER);
    }

    return courses.stream().map(course -> {
      List<Chapter> listChapter = chapterRepository.findChaptersByCourse_Id(course.getId());
      Provider provider = providerRepository.findById(course.getProvider().getId())
              .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND));
      Enrollment enrollment = enrollmentsRepository.findEnrollmentByCourse_Id(course.getId());
      Review review = reviewRepository.findReviewByEnrollmentId(enrollment.getId());
      ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(review.getRating(),review.getComment());
      return new CourseResponseDTO(course, listChapter, provider,reviewResponseDTO);
    }).collect(Collectors.toList());
  }


  public List<CourseResponseDTO> getCourseByTopic(int topicId) {
    if (!courseRepository.existsById(topicId)) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND_FOR_TOPIC);
    }

    List<Course> courses = courseRepository.findCoursesByTopicId(topicId);

    return courses.stream().map(course -> {
      List<Chapter> listChapter = chapterRepository.findChaptersByCourse_Id(course.getId());
      Provider provider = providerRepository.findById(course.getProvider().getId())
              .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND));
      Enrollment enrollment = enrollmentsRepository.findEnrollmentByCourse_Id(course.getId());
      Review review = reviewRepository.findReviewByEnrollmentId(enrollment.getId());
      ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(review.getRating(),review.getComment());
      return new CourseResponseDTO(course, listChapter, provider,reviewResponseDTO);
    }).collect(Collectors.toList());
  }

}
