package com.gfcf14.webdevtoons.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gfcf14.webdevtoons.models.Post;

public interface PostRepository extends JpaRepository<Post, String> {
  @Query(nativeQuery = true, value = "SELECT * FROM posts ORDER BY date DESC")
  List<Post> findPostsSortedByDate();
}
