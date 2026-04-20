package com.rahim.syntopicalnotes.services.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JWTService {
    
    private final String secret_key = "239rjf329f8w3fejwe98fwe9f932j9gj549";
    private final Long expirationDate = 1000l * 60 * 60 * 24 * 240;


    public String generateToken(Long id) {

        Map<String, Object> claims = new HashMap<>();
        
        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(String.valueOf(id))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+ expirationDate))
            .signWith(getSecretKey())
            .compact();
    }

    public Long extractSubject(String token) {
        String stringId = extractAClaim(token, Claims::getSubject);
        return Long.parseLong(stringId);
    }

    private <T> T extractAClaim(String token, Function<Claims, T> claimFunction) {
        Claims claims = extractAllClaims(token);
        return claimFunction.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJwt(token)
            .getBody();
    }

    private SecretKey getSecretKey() {
        byte[] secretByteKey = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(secretByteKey);
    }
}
