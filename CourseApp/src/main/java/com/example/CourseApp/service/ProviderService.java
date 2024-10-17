package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Provider;
import com.example.CourseApp.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public List<Provider> getProviders() {
        return providerRepository.findAll();
    }
}
