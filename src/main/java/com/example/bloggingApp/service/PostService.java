package com.example.bloggingApp.service;

import com.example.bloggingApp.model.Post;
import com.example.bloggingApp.payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
    PostDTO updatePost(PostDTO postDTO,Integer id);
    void deletePost(Integer id);
    List<PostDTO> getAllPost(Integer pageSize,Integer pageNumber,String sortBy);
    PostDTO getPostById(Integer id);
    List<PostDTO> getAllPostByUser(Integer userId);
    List<PostDTO> getAllPostByCategory(Integer categoryId);
    List<PostDTO> searchPostByKeyword(String keyword);
}
