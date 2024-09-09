package com.example.CourseApp.service.user;

import com.example.CourseApp.model.user.AppUser;
import com.example.CourseApp.model.user.RefreshToken;
import com.example.CourseApp.repository.AppUserRepository;
import com.example.CourseApp.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    JWTService jwtService;

    public RefreshToken generateRefreshToken(String username){
        AppUser appUser = userRepository.getAppUserByUsername(username);
        String token =  jwtService.generateRefreshToken(appUser);
        RefreshToken refreshToken =  RefreshToken.builder()
                .token(token)
                .expired(false)
                .user(appUser)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken isRefreshTokenValid(RefreshToken refreshToken){
        if(!jwtService.isTokenExpried(refreshToken.getToken()))
            return refreshToken;
        else {
            refreshTokenRepository.delete(findByToken(refreshToken.getToken()).get());
            throw new RuntimeException(refreshToken + " Refresh token is expired. Please make a new login..!");
        }

    }

}
