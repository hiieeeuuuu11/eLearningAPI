package com.example.CourseApp.controller;

import com.example.CourseApp.dto.response.SignInResponse;
import com.example.CourseApp.model.user.AppUser;
import com.example.CourseApp.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @PostMapping("/signup")
    public ResponseEntity<AppUser> signup(@RequestParam String username,@RequestParam String password,@RequestParam int role){
        AppUser appUser= appUserService.signup(username,password,role);
        return ResponseEntity.ok(appUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signin(@RequestParam String username, @RequestParam String password){
        SignInResponse signInResponse = appUserService.signin(username,password);
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<SignInResponse> refreshToken(@RequestParam String refreshtoken){
        SignInResponse refreshTokenResponse = appUserService.refreshToken(refreshtoken);
        return ResponseEntity.ok(refreshTokenResponse);
    }

}
