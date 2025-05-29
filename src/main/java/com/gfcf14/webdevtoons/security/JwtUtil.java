package com.gfcf14.webdevtoons.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String jwtSecret;

  private static final long TOKEN_TIME_LIMIT = 15 * 60 * 1000; // 15 minutes

  public String generateToken(String username, boolean canPost) {
    Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    return Jwts.builder()
            .setSubject(username)
            .claim("canPost", canPost)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_TIME_LIMIT))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  public String validateTokenAndGetUsername(String token) {
    try {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    } catch (JwtException e) {
      return null; // invalid token
    }
  }

  public boolean isTokenValid(String token) {
    return validateTokenAndGetUsername(token) != null;
  }

  public boolean canPost(String token) {
    try {
      Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
      var claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
      return Boolean.TRUE.equals(claims.get("canPost", Boolean.class));
    } catch (JwtException e) {
      return false;
    }
  }
}
