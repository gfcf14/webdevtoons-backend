package com.gfcf14.webdevtoons.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gfcf14.webdevtoons.models.Post;
import com.gfcf14.webdevtoons.repositories.PostRepository;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAllPosts() {
        return repository.findPostsSortedByDate();
    }

    public Optional<Post> getPostByDate(String date) {
        return repository.findById(date);
    }

    public Post createPost(Post post) {
        return repository.save(post);
    }

    public Optional<Post> findClosestOrEarliest(String date) {
        return this.repository.findClosestPostOnOrBefore(date)
            .or(() -> this.repository.findEarliestPost());
    }
}
