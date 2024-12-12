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

  public static final String HOST = "https://e140-2405-4802-1bf4-fe80-14f4-454a-d361-25ea.ngrok-free.app/";

  public static final  String URL_MI = HOST + "MI_recommend/";


  public static final String ADD_COURSE = HOST + "add_course/";

  public static final String RATE = HOST + "rate/";

  public static final String ENROLL = HOST + "enroll/";

  public static final String LSTM = HOST + "LSTM_recommend/";

  public static final String URL_MF = HOST + "MF_recommend/";

  public static final String URL_BERT = HOST + "BERT_search/";

  private final RestTemplate restTemplate;

  public String callExternalApi(String url) {
    return restTemplate.getForObject(url, String.class);
  }

  public List<Integer> getRecommendCourseIdsMI(Long courseid) {
    return convertStringToList(callExternalApi(URL_MI+courseid));
  }

  public List<Integer> getRecommendCourseIdsMF(Long u) {
    return convertStringToList(callExternalApi(URL_MF+u));
  }

  public List<Integer> getRecommendCourseIdsLSTM(Long u) {
    return convertStringToList(callExternalApi(LSTM+u));
  }

  public List<Integer> getSearchedIdsBERT(String query) {
    return convertStringToList(callExternalApi(URL_BERT+query));
  }

  public static List<Integer> convertStringToList(String str) {
    str = str.replaceAll("\\[|\\]|\\n", ""); // Remove square brackets
    return Arrays.stream(str.split(","))
        .map(item-> Integer.parseInt(item.trim())+1)
        .collect(Collectors.toList());
  }

  public String addCourse(String courseName) {
    return restTemplate.getForObject(ADD_COURSE + courseName, String.class);
  }

  public String rating(Integer uid, Integer courseid, Integer rating) {
    return restTemplate.getForObject(RATE + uid + "/" +courseid +"/" + rating, String.class);
  }

  public String enroll(Integer uid, Integer courseid) {
    return restTemplate.getForObject(ENROLL + uid + "/" +courseid +"/" + Long.MAX_VALUE, String.class);
  }

}
