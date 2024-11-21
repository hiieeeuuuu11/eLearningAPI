package com.example.CourseApp.service;

import com.example.CourseApp.dto.response.ApiResConfigDTO;
import com.example.CourseApp.dto.response.CourseResponseDTO;
import com.example.CourseApp.dto.response.ReviewResponseDTO;
import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.entity.course.Enrollment;
import com.example.CourseApp.entity.course.Provider;
import com.example.CourseApp.entity.course.Review;
import com.example.CourseApp.exceptions.BadRequestException;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.ChapterRepository;
import com.example.CourseApp.repository.CourseRepository;
import com.example.CourseApp.repository.EnrollmentsRepository;
import com.example.CourseApp.repository.ProviderRepository;
import com.example.CourseApp.repository.ReviewRepository;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;
  private final ChapterRepository chapterRepository;
  private final ProviderRepository providerRepository;
  private final EnrollmentsRepository enrollmentsRepository;
  private final ReviewRepository reviewRepository;

  public List<CourseResponseDTO> getAll() {
    List<Course> courses = courseRepository.findAll();

    if (courses.isEmpty()) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND);
    }

    return courses.stream().map(this::mapToCourseResponseDTO).collect(Collectors.toList());
  }

  public List<CourseResponseDTO> getAllCourse() {
    List<Course> courses = courseRepository.findAll();

    if (courses.isEmpty()) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND);
    }

    return courses.stream().map(this::mapToCourseResponseDTO).collect(Collectors.toList());
  }

  public CourseResponseDTO getCourseById(int course_id) {
    Course course = courseRepository
        .findById(course_id)
        .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.COURSE_NOT_FOUND));
    return mapToCourseResponseDTO(course);
  }

  public List<CourseResponseDTO> getCourseByProvider(int providerId) {
    if (!providerRepository.existsById(providerId)) {
      throw new BadRequestException("Provider không tồn tại với ID: " + providerId);
    }

    List<Course> courses = courseRepository.findCoursesByProviderId(providerId);

    if (courses.isEmpty()) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND_FOR_PROVIDER);
    }

    return courses.stream().map(this::mapToCourseResponseDTO).collect(Collectors.toList());
  }

  public ApiResConfigDTO checkCourseEnrollment(int student_id, int course_id){
    boolean status = enrollmentsRepository.existsByLearnerIdAndCourseId(student_id, course_id);
    Integer id = enrollmentsRepository.findEnrollmentByCourseIdAndLearnerId(course_id, student_id).getId();
    return new ApiResConfigDTO(status, id);
  }
  public int getReviewRating(int studentId,int courseId){
    int rating=0;

    if(reviewRepository.existsByCourseIdAndStudentId(courseId,studentId)){
      Optional<Review> review = reviewRepository.findReviewByCourseIdAndStudentId(courseId,studentId);
      rating = review.get().getRating();
    }
    return rating;
  }
  public void updateReviews(int enrollment_id,int rating){
    if(!reviewRepository.existsByEnrollmentId(enrollment_id)){
      Review review = new Review();
      review.setRating(rating);
      review.setEnrollment(enrollmentsRepository.findById(enrollment_id)
              .orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_ENROLLMENT_FOUND)));
      review.setComment("");
      review.setCreated_at(LocalDateTime.now());
      reviewRepository.save(review);
    }else{
      Review review = reviewRepository.findReviewByEnrollmentId(enrollment_id);
      review.setRating(rating);
      review.setEnrollment(enrollmentsRepository.findById(enrollment_id)
              .orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_ENROLLMENT_FOUND)));
      review.setComment("");
      review.setCreated_at(LocalDateTime.now());
      reviewRepository.save(review);
    }
  }
  public List<CourseResponseDTO> getCourseByTopic(int topicId) {
    if (!courseRepository.existsById(topicId)) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND_FOR_TOPIC);
    }

    List<Course> courses = courseRepository.findCoursesByTopicId(topicId);

    return courses.stream().map(this::mapToCourseResponseDTO).collect(Collectors.toList());
  }

  private CourseResponseDTO mapToCourseResponseDTO(Course course) {
    List<Chapter> listChapter = chapterRepository.findChaptersByCourse_Id(course.getId());
    Provider provider = providerRepository.findById(course.getProvider().getId())
        .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND));
    Enrollment enrollment = enrollmentsRepository.findEnrollmentByCourse_Id(course.getId());

    if (enrollment == null) {
      return buildCourseResponseDTO(course, listChapter, provider, null);
    }

    Review review = reviewRepository.findReviewByEnrollmentId(enrollment.getId());
    ReviewResponseDTO reviewResponseDTO =
        (review != null) ? new ReviewResponseDTO(review.getRating(), review.getComment()) : null;

    return buildCourseResponseDTO(course, listChapter, provider, reviewResponseDTO);
  }

  private CourseResponseDTO buildCourseResponseDTO(Course course, List<Chapter> listChapter,
      Provider provider, ReviewResponseDTO reviewResponseDTO) {
    return CourseResponseDTO.builder()
        .course(course)
        .chapterList(listChapter)
        .provider(provider)
        .reviewResponseDTO(reviewResponseDTO)
        .build();
  }

}
