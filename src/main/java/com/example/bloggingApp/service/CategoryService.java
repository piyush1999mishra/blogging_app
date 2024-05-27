package com.example.bloggingApp.service;

import com.example.bloggingApp.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer id);
    void deleteCategory(Integer id);
    CategoryDTO getCategoryById(Integer id);
    List<CategoryDTO> getAllCategory();
}
