package com.gfcf14.webdevtoons.controllers;

import com.gfcf14.webdevtoons.models.Post;
import com.gfcf14.webdevtoons.security.JwtUtil;
import com.gfcf14.webdevtoons.services.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private JwtUtil jwtUtil;

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/{date}")
    public ResponseEntity<Post> getPost(@PathVariable String date) {
        return service.getPostByDate(date)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPost(
        @RequestBody Post post,
        @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid token format");
        }

        String token = authHeader.substring(7); // Remove "Bearer "
        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        service.createPost(post);
        return ResponseEntity.ok("Post " + post.getTitle() + " successfully created");
    }

}
