package com.gfcf14.webdevtoons.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gfcf14.webdevtoons.model.Post;
import com.gfcf14.webdevtoons.repository.PostRepository;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Optional<Post> getPostByDate(String date) {
        return repository.findById(date);
    }

    public Post createPost(Post post) {
        return repository.save(post);
    }
}
