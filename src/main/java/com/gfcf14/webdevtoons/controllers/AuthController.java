package com.gfcf14.webdevtoons.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfcf14.webdevtoons.models.AuthRequest;
import com.gfcf14.webdevtoons.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class AuthController {
  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private JwtUtil jwtUtil;

  public boolean isValidDbUser(String username, String password) {
    try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
      return true; // connection successful
    } catch (SQLException e) {
      return false; // invalid credentials
    }
}

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    boolean valid = isValidDbUser(request.getUsername(), request.getPassword());

    if (valid) {
      String token = jwtUtil.generateToken(request.getUsername());

      return ResponseEntity.ok(Map.of("token", token));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
  }
}
