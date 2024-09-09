package com.example.CourseApp.service.user;

import com.example.CourseApp.model.user.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${application.security.jwt.secret_key}")
    private String secretKey ;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh_token.expiration}")
    private long refreshExpiration;

    public String generateToken(AppUser user){
        return generateToken(new HashMap<>(),user);
    }

    public String generateToken(Map<String,Object> claims, AppUser user){
        return jwtBuilder(claims,user,jwtExpiration);
    }

    public String generateRefreshToken(AppUser user){
        return jwtBuilder(new HashMap<>(),user,refreshExpiration);
    }

    public String jwtBuilder(Map<String,Object> claims, AppUser user,long expiration){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        Function<Claims,String> extract = Claims::getSubject;
        return extract.apply(extractClaim(token));
    }

    public Date extractExpiration(String token){
        Function<Claims,Date> extract = Claims::getExpiration;
        return extract.apply(extractClaim(token));
    }

    public boolean isTokenExpried(String token) {
        Date date = extractExpiration(token);
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token,UserDetails user){
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpried(token);
    }

}
