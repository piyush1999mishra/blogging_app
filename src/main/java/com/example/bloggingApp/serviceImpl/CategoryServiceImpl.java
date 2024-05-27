package com.example.bloggingApp.serviceImpl;

import com.example.bloggingApp.exceptions.ResourceNotFoundException;
import com.example.bloggingApp.model.Category;
import com.example.bloggingApp.payload.CategoryDTO;
import com.example.bloggingApp.payload.UserDTO;
import com.example.bloggingApp.repository.CategoryRepo;
import com.example.bloggingApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO,Category.class);
        Category savedCategory=categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id) {
        Category category=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","id",id));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(category.getCategoryDescription());
        Category savedCategory=categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);

    }

    @Override
    public void deleteCategory(Integer id) {
        Category category=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","id",id));
        categoryRepo.delete(category);

    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Category category=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","id",id));
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories=categoryRepo.findAll();
        List<CategoryDTO> categoryDTOS=categories.stream().map(t->modelMapper.map(t,CategoryDTO.class)).collect(Collectors.toList());
        return categoryDTOS;
    }
}
