package com.gfcf14.webdevtoons.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gfcf14.webdevtoons.models.User;

public interface UserRepository extends JpaRepository<User, String> {
}
