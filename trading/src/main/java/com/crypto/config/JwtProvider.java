package com.crypto.config;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());


    public static String generateToken(Authentication auth) {
        Collection <? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populatedAuthorities(authorities);

        String jwt = Jwts.builder()
            .subject(auth.getName())
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plus(24, ChronoUnit.HOURS)))
            .claim("email", auth.getName())
            .claim("authorities", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
            .signWith(key)
            .compact();

            return jwt;
    }

    public static String getEmailFromToken(String token) {
        token = token.substring(7);

        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

        String email = String.valueOf(claims.get("email"));

        return email;

    }

    private static String populatedAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();

        for(GrantedAuthority ga:authorities){
            auth.add(ga.getAuthority());
        }

        return String.join(",", auth);
    }
}
