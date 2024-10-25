package com.example.CourseApp.controller;

import com.example.CourseApp.entity.course.Provider;
import com.example.CourseApp.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider-courses")
@Tag(name = "Provider", description = "APIs for get provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping
    @Operation(summary = "Lấy tất cả nhà cung cấp")
    @ApiResponse(responseCode = "200", description = "Lấy thành công")
    public List<Provider> getAllProviders() {
        return providerService.getProviders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy nhà cung cấp theo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công"),
            @ApiResponse(responseCode = "404", description = "Nhà cung cấp không tồn tại")
    })
    public Provider getProviderById(@PathVariable int id) {
        return providerService.getProviderById(id);
    }
}
