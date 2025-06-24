package com.gfcf14.webdevtoons.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gfcf14.webdevtoons.models.Post;

public interface PostRepository extends JpaRepository<Post, String> {
  @Query(nativeQuery = true, value = "SELECT * FROM posts ORDER BY date DESC")
  List<Post> findPostsSortedByDate();

  @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE date <= :date ORDER BY date DESC LIMIT 1")
  Optional<Post> findClosestPostOnOrBefore(@Param("date") String date);

  @Query(nativeQuery = true, value = "SELECT * FROM posts ORDER BY date ASC LIMIT 1")
  Optional<Post> findEarliestPost();
}
