package com.gfcf14.webdevtoons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfcf14.webdevtoons.model.Post;

public interface PostRepository extends JpaRepository<Post, String> {}
