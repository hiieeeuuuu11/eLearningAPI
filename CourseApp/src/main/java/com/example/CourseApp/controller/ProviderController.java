package com.example.CourseApp.controller;

import com.example.CourseApp.entity.course.Provider;
import com.example.CourseApp.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider-courses")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService ProviderService;
    

    @GetMapping
    public List<Provider> getAllProviders() {
        return ProviderService.getProviders();
    }

    @GetMapping("/{id}")
    public Provider getProviderById(@PathVariable int id) {
        return ProviderService.getProviderById(id);
    }

    @PostMapping
    public Provider createProvider(@RequestBody Provider Provider) {
        return ProviderService.createProvider(Provider);
    }

    @PutMapping("/{id}")
    public Provider updateProvider(@PathVariable int id, @RequestBody Provider Provider) {
        return ProviderService.updateProvider(id, Provider);
    }

    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable int id) {
        ProviderService.deleteProvider(id);
    }


}