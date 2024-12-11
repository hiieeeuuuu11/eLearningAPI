package com.example.CourseApp.service;

import com.example.CourseApp.entity.course.Provider;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.ProviderRepository;
import com.example.CourseApp.repository.UserRepository;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderService {
  private final ProviderRepository providerRepository;
  private final UserRepository userRepository;

  public List<Provider> getProviders() {
    List<Provider> providers = providerRepository.findAll();
    if (providers.isEmpty()) {
      throw new ObjectNotFoundException(ResponseStatusCodeConst.NO_PROVIDER_FOUND);
    }
    return providers;
  }

  public Provider getProviderById(int id) {
    return providerRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND));
  }

  public Provider createProvider(Provider provider) {
      if(providerRepository.existsById(provider.getId())){
          throw new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_EXISTED);
      }
      provider.setUser(userRepository.findById(1).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND)));
    return providerRepository.save(provider);
  }

  public Provider updateProvider(int id, Provider provider) {
      if(!providerRepository.existsById(id)){
          throw new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND);
      }
    return providerRepository.save(provider);
  }

  public void deleteProvider(int id) {
      if(!providerRepository.existsById(id)){
          throw new ObjectNotFoundException(ResponseStatusCodeConst.PROVIDER_NOT_FOUND);
      }
    providerRepository.deleteById(id);
  }
}
