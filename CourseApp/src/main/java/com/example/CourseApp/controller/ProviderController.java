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

  @Autowired private ProviderService providerService;

  @GetMapping
  public List<Provider> getAllProviders() {
    return providerService.getProviders();
  }

  @GetMapping("/{id}")
  public Provider getProviderById(@PathVariable int id) {
    return providerService.getProviderById(id);
  }

  @PostMapping
  public Provider createProvider(@RequestBody Provider provider) {
    return providerService.createProvider(provider);
  }

  @PutMapping("/{id}")
  public Provider updateProvider(@PathVariable int id, @RequestBody Provider Provider) {
    return providerService.updateProvider(id, Provider);
  }

  @DeleteMapping("/{id}")
  public void deleteProvider(@PathVariable int id) {
    providerService.deleteProvider(id);
  }
}
