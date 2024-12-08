package com.example.CourseApp.controller;

import com.example.CourseApp.dto.response.ApiResConfigDTO;
import com.example.CourseApp.dto.response.CourseResponseDTO;
import com.example.CourseApp.dto.response.OrderRequestDTO;
import com.example.CourseApp.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
@Tag(name = "Course", description = "APIs for get course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    @Operation(summary = "Lấy tất cả các khóa học")
    @ApiResponse(responseCode = "200", description = "Lấy thành công")
    public List<CourseResponseDTO> getAllProviderCourses() {
        return courseService.getAllCourse();
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
//    @PostMapping("/updateReviews")
//    @Operation(summary = "Cập nhật bảng reviews")
//    public void updateReviews(@RequestParam int enrollmentId, @RequestParam int rating) {
//        courseService.updateReviews(enrollmentId,rating);
//    }
    @GetMapping("/getRating")
    @Operation(summary = "Kiểm tra khóa học đã đăng ký hay chưa")
    public int getReviewRating(@RequestParam int studentId, @RequestParam int courseId) {
        return courseService.getReviewRating(studentId,courseId);

    }
}
