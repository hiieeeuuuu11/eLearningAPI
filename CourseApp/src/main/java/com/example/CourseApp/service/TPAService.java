package com.example.CourseApp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TPAService {

  public static final  String URL = "https://6907-14-191-39-251.ngrok-free.app/MI_recommend/7";

  private final RestTemplate restTemplate;

  public String callExternalApi(String url) {
    return restTemplate.getForObject(url, String.class);
  }

  public static List<Integer> convertStringToList(String str) {
    str = str.replaceAll("\\[|\\]", ""); // Remove square brackets
    return Arrays.stream(str.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  public static List<Integer> getRecommendCourseIds(String response) {
    return convertStringToList(response);
  }

}
