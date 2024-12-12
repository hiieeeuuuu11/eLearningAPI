package com.example.CourseApp.controller;

import com.example.CourseApp.dto.UserInfo;
import com.example.CourseApp.dto.response.ApiResConfigDTO;
import com.example.CourseApp.dto.response.CourseResponseDTO;
import com.example.CourseApp.entity.course.Course;
import com.example.CourseApp.service.CourseService;
import com.example.CourseApp.service.TPAService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
@Tag(name = "Course", description = "APIs for get course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    TPAService tpaService;

    @GetMapping
    @Operation(summary = "Lấy tất cả các khóa học")
    @ApiResponse(responseCode = "200", description = "Lấy thành công")
    public List<CourseResponseDTO> getAllProviderCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping("/recommend/lstm")
    @Operation(summary = "Gợi ý khóa học")
    @ApiResponse(responseCode = "200", description = "Lấy thành công")
    public List<Course> recommendLSTM(@RequestAttribute(name = "user", required = false) UserInfo user) {
        return courseService.getByIds(tpaService.getRecommendCourseIdsLSTM(user.getId()));
    }

    @GetMapping("/recommend/mf")
    @Operation(summary = "Gợi ý khóa học")
    @ApiResponse(responseCode = "200", description = "Lấy thành công")
    public List<Course> recommendMF(@RequestAttribute(name = "user", required = false) UserInfo user) {
        return courseService.getByIds(tpaService.getRecommendCourseIdsMF(user.getId()));
    }

    @GetMapping("/recommend/mi/{courseId}")
    @Operation(summary = "Gợi ý khóa học")
    @ApiResponse(responseCode = "200", description = "Lấy thành công")
    public List<Course> recommendMI(@PathVariable("courseId") Long courseId) {
        return courseService.getByIds(tpaService.getRecommendCourseIdsMI(courseId));
    }

    @GetMapping("/bert/{query}")
    @Operation(summary = "Gợi ý khóa học")
    @ApiResponse(responseCode = "200", description = "Lấy thành công")
    public List<Course> recommendMI(@PathVariable("query") String q) {
        return courseService.getByIds(tpaService.getSearchedIdsBERT(q));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy khóa học theo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Khóa học không tồn tại")
    })
    public ResponseEntity<CourseResponseDTO> getProviderCourseById(@PathVariable("id") String id) {
        int id1 = Integer.parseInt(id);
        return ResponseEntity.ok(courseService.getCourseById(id1));
    }

    @GetMapping("/getbyprovider")
    @Operation(summary = "Lấy khóa học theo nhà cung cấp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy khóa học cho nhà cung cấp")
    })
    public ResponseEntity<List<CourseResponseDTO>> getCourseByProvider(@RequestParam("provider_id") String id) {
        int id1 = Integer.parseInt(id);
        return ResponseEntity.ok(courseService.getCourseByProvider(id1));
    }

    @GetMapping("/getbytopic")
    @Operation(summary = "Lấy khóa học theo chủ đề")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy khóa học cho chủ đề")
    })
    public ResponseEntity<List<CourseResponseDTO>> getCourseByTopic(@RequestParam("topic_id") String id) {
        int id1 = Integer.parseInt(id);
        return ResponseEntity.ok(courseService.getCourseByTopic(id1));
    }
    @GetMapping("/checkEnrollment")
    @Operation(summary = "Kiểm tra khóa học đã đăng ký hay chưa")
    public ApiResConfigDTO checkEnrollment(@RequestParam int studentId, @RequestParam int courseId) {
        return courseService.checkCourseEnrollment(studentId,courseId);

    }
    @PostMapping("/updateReviews")
    @Operation(summary = "Cập nhật bảng reviews")
    public void updateReviews(@RequestParam int enrollmentId, @RequestParam int rating) {
        courseService.updateReviews(enrollmentId,rating);
    }
    @GetMapping("/getRating")
    @Operation(summary = "Kiểm tra khóa học đã đăng ký hay chưa")
    public int getReviewRating(@RequestParam int studentId, @RequestParam int courseId) {
        return courseService.getReviewRating(studentId,courseId);

    }

    @GetMapping("/getcoursebyuser")
    @Operation(summary = "Lấy khóa học theo user")
    public List<Course> getReviewRating(@RequestAttribute(name = "user", required = false) UserInfo user) {
        return courseService.getByUser(user.getId());
    }
}
