package com.rahim.syntopicalnotes.services.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    private final String secret;
    private final Long expirationTime;

    public JWTService(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration-ms}") String expirationTime
    ) {
        this.secret = secret;
        this.expirationTime = Long.parseLong(expirationTime);
    }

    public String generateJWTToken(Long id) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts
            .builder()
            .setHeader(claims)
            .setSubject(String.valueOf(id))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + this.expirationTime))
            .signWith(this.getSecretKey())
            .compact();
    }

    public Long extractSubjectFromJwtToken(String token) {
        String subjectString = extractClaim(token, Claims::getSubject);
        return Long.parseLong(subjectString);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimExtractor) {
        Claims allClaims = this.extractAllClaims(token);
        return claimExtractor.apply(allClaims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(this.getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private SecretKey getSecretKey() {
       byte[] decodedSecretKey = Decoders.BASE64.decode(this.secret); 
       return Keys.hmacShaKeyFor(decodedSecretKey);
    }
}
