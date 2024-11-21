package com.example.CourseApp.controller;

import com.example.CourseApp.dto.response.BaseResponse;
import com.example.CourseApp.dto.response.ChapterResponse;
import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.service.ChapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/chapter")
@Tag(name = "Chapter", description = "APIs for get chapter")
@RequiredArgsConstructor
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping
    @Operation(summary = "Lấy tất cả các chương")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy chương")
    })
    public List<Chapter> getAllProviderChapters() {
        return chapterService.getAllChapter();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy chương theo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Chương không tồn tại")
    })
    public ResponseEntity<ChapterResponse> getChapterById(@PathVariable("id") int id) {
        return ResponseEntity.ok(chapterService.getChapterById(id));
    }

    @GetMapping("/getbycourse")
    @Operation(summary = "Lấy chương theo khóa học")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy chương cho khóa học")
    })
    public ResponseEntity<List<Chapter>> getChapterByCourse(@RequestParam("course_id") int id) {
        return ResponseEntity.ok(chapterService.getChapterByCourse(id));
    }

    @GetMapping("/getnumofChap")
    @Operation(summary = "Đếm số lượng chương theo khóa học")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đếm thành công"),
            @ApiResponse(responseCode = "404", description = "Khóa học không tồn tại")
    })
    public ResponseEntity<BaseResponse<?>> getNumberOfChapter(@RequestParam("course_id") int id) {
        return ResponseEntity.ok(BaseResponse.builder().data(chapterService.getNumberOfChapter(id)).build());
    }

}
