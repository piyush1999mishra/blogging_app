package com.example.bloggingApp.repository;

import com.example.bloggingApp.model.Category;
import com.example.bloggingApp.model.Post;
import com.example.bloggingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
