package com.gfcf14.webdevtoons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfcf14.webdevtoons.models.Post;

public interface PostRepository extends JpaRepository<Post, String> {}
