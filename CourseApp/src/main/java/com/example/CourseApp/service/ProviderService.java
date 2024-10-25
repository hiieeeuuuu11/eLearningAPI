package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Provider;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.ProviderRepository;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {
  private final ProviderRepository providerRepository;

  public List<Provider> getProviders() {
    List<Provider> providers = providerRepository.findAll();
    if (providers.isEmpty()) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_PROVIDER_FOUND);
    }
    return providers;
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
