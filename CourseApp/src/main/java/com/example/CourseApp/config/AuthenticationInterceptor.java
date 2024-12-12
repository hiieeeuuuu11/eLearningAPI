package com.example.CourseApp.config;

import com.example.CourseApp.dto.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final RestTemplate restTemplate;

    public AuthenticationInterceptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                // Gọi tới authentication server
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(jwt);
                HttpEntity<String> entity = new HttpEntity<>(headers);
                
                ResponseEntity<UserInfo> userResponse = restTemplate.exchange(
                    "http://localhost:8082/api/v1/auth/check_auth",
                    HttpMethod.GET,
                    entity,
                    UserInfo.class
                );
                
                if (userResponse.getStatusCode() == HttpStatus.OK && userResponse.getBody() != null) {
                    request.setAttribute("user", userResponse.getBody());
                    return true;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}