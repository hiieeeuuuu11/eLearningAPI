package com.example.CourseApp.service.user;

import com.example.CourseApp.dto.response.SignInResponse;
import com.example.CourseApp.model.user.AppUser;
import com.example.CourseApp.model.user.RefreshToken;
import com.example.CourseApp.model.user.Role;
import com.example.CourseApp.repository.AppUserRepository;
import com.example.CourseApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RefreshTokenService refreshTokenService;

    public AppUser signup(String username,String password,int role){
        if(appUserRepository.getAppUserByUsername(username)==null){
            Role role1 = roleRepository.getReferenceById(role);
            AppUser appUser = AppUser.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(role1)
                    .build();
            return appUserRepository.save(appUser);
        }
        else return null;
    }

    public SignInResponse signin(String username, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        String username1 = authentication.getName();
        String accessToken = jwtService.generateToken(AppUser.builder().username(username1).build());
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(username1);
        return new SignInResponse(accessToken,refreshToken.getToken());

    }

    public SignInResponse refreshToken(String refreshToken1){
        return refreshTokenService.findByToken(refreshToken1)
                .map(refreshToken2 -> refreshTokenService.isRefreshTokenValid(refreshToken2))
                .map(item ->{
                    String accessToken = jwtService.generateToken(item.getUser());
                    return new SignInResponse(accessToken,item.getToken());
                })
                .orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

}
