package com.gfcf14.webdevtoons.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfcf14.webdevtoons.models.AuthRequest;
import com.gfcf14.webdevtoons.models.User;
import com.gfcf14.webdevtoons.repositories.UserRepository;
import com.gfcf14.webdevtoons.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class AuthController {
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    Optional<User> dbUser = userRepository.findById(request.getUsername());

    if (dbUser.isPresent()) {
      User user = dbUser.get();
      boolean passwordMatch = BCrypt.checkpw(request.getPassword(), user.getPassword());

      if (passwordMatch) {
        String token = jwtUtil.generateToken(request.getUsername(), true);

        return ResponseEntity.ok(Map.of("token", token));
      }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }
}
