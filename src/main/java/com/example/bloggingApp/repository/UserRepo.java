package com.example.bloggingApp.repository;

import com.example.bloggingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
