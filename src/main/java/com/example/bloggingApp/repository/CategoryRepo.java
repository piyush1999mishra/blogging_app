package com.example.bloggingApp.repository;

import com.example.bloggingApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
