package com.testAce.TestAceBackend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtService {


    private final String secretKey = "7d3e9c66edef6af626dbac46045ef8692ecd0bd29d77e51ae98d2a5fe2437daa8b9f29b19dda8700f838baba612954498c32eed1837ef9ddb995e46e687a28baaf834534ba97b5c342bca3cc48dd12bf42cbfef743bc6972e10e9b37e22dcc7145e7a7c8b99373b53c95e6cb4d6762935a21093403819f451a4f33356af0f9ebbccfc55053a14b2174b81c14d537034266f9042995f7e0e2f0734301b24ffed0a0d2329b4d9267210db8d32e6e3417a875475f78beb51511bb763229acefa1b6ff9ab8c58432db4c8e987ad082159d427020dece65e39b518f508c2e33a48f01d156bf9cebb9e7422971e944de8ca65925c65cf6de8d0b719541b85d05d1e7f3";

    public String getToken(String email) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuer("DCB")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecrateKey())
                .compact();
    }

    private SecretKey getSecrateKey() {
        byte[] decode = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(decode);
    }

    public String extractUsername(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
        Claims claims = extractClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String jwtToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final  String username = extractUsername(jwtToken);

        return (username.equals(userDetails.getUsername()) && !tokenExpried(jwtToken));
    }

    private boolean tokenExpried(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration).before(new Date());
    }
}
