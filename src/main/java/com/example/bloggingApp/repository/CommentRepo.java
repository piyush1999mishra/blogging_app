package com.example.bloggingApp.repository;

import com.example.bloggingApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
