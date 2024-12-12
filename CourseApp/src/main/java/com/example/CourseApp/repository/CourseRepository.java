package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findCoursesByProviderId(int provider_id);
    Course findCourseByProviderId(int provider_id);
    boolean existsByProviderId(int provider_id);
    List<Course> findCoursesByTopicId(int topic_id);

    @Query(value = """
        select *
        from courses
        where id in (
            select e.course_id
            from users u
            join learner on u.id = learner.user_id
            join enrollments e on learner.id = e.student_id
            where user_id = :uid
        )
        """, nativeQuery = true)
    List<Course> getCourseEnrolledByUser(@Param("uid") Long user_id);

}