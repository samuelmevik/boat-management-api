package com.example.demo.config;

import java.security.Key;
import java.util.Map;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    // TODO: Move to env variable, bad practice to have it hardcoded, and a security risk.
    private final String SECRET_KEY = "JKvgeliISHNdpHanR4xxesFj/B6OcFTq6YiqIyjVwADnga27kyqKwqY+IwlUHDqXeDhpZqbV1nQKXhfYh6sVanhaqWvz2kvydWORmDOEukzuQJzxt61cV+unaQGNHlfSHXEaWbBBWm3TbeySclQ/2qhBPErx58w84Lzp4ikdA1sotjQC7QXrV5YktrqgHCIu1sViWqZ9AiwOE5kqcxd63LUirBICmqDi17Ru9KIeUA1lQ9BA56bw34bePxcA/V8OV+f21BFyFyIC26uusfGdq7RhjaN7Q39ER/54sa6FON+lFuecnpJwVt8Gyyo9OzjXy/m97wno9O7T7Hi55CQ9Qx1jmQeA27uzDvSqG0VdbNI=\n";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String emailFromRequest(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        return extractUsername(token);
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    // Generates a JWT token with specified claims, subject, issue date, expiration, and signing key
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 1000 * 60 * 60 * 10)) // TODO: Move to env variable
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) 
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }



    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
