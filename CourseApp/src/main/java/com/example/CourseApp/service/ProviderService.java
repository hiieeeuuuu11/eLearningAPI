package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Provider;
import com.example.CourseApp.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    private  ProviderRepository providerRepository;
    public List<Provider> getProviders() {
        return providerRepository.findAll();
    }
    public Provider getProviderById(int id) {
        return providerRepository.findById(id);
    }
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }
    public Provider updateProvider(int id, Provider provider) {
        return providerRepository.save(provider);
    }
    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }
}
