package com.example.bloggingApp.serviceImpl;

import com.example.bloggingApp.exceptions.ResourceNotFoundException;
import com.example.bloggingApp.model.Category;
import com.example.bloggingApp.model.Post;
import com.example.bloggingApp.model.User;
import com.example.bloggingApp.payload.CategoryDTO;
import com.example.bloggingApp.payload.PostDTO;
import com.example.bloggingApp.payload.UserDTO;
import com.example.bloggingApp.repository.CategoryRepo;
import com.example.bloggingApp.repository.PostRepo;
import com.example.bloggingApp.repository.UserRepo;
import com.example.bloggingApp.service.CategoryService;
import com.example.bloggingApp.service.PostService;
import com.example.bloggingApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId) {
        //UserDTO userDTO=userService.getUserById(userId);
        //CategoryDTO categoryDTO=categoryService.getCategoryById(categoryId);
        //postDTO.setCategoryDTO(categoryDTO);
        //postDTO.setUserDTO(userDTO);
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        Post post=modelMapper.map(postDTO,Post.class);
        post.setCategory(category);
        post.setUser(user);
        Post createdPost=postRepo.save(post);
        return modelMapper.map(createdPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer id) {
        Post post=postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","postId",id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        Post createdPost=postRepo.save(post);
        return modelMapper.map(createdPost,PostDTO.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post=postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","postId",id));
        postRepo.delete(post);

    }

    @Override
    public List<PostDTO> getAllPost(Integer pageSize,Integer pageNumber,String sortBy) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Post> posts=postRepo.findAll(pageable);
        List<Post> postList=posts.getContent();
        List<PostDTO> postDTOS=postList.stream().map(t->modelMapper.map(t,PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post=postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","postId",id));
        PostDTO postDTO=modelMapper.map(post,PostDTO.class);
        return postDTO;
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        UserDTO userDTO=userService.getUserById(userId);
        User user=modelMapper.map(userDTO,User.class);
        List<Post> posts=postRepo.findByUser(user);
        List<PostDTO> postDTOS=posts.stream().map(t->modelMapper.map(t,PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {
        CategoryDTO categoryDTO=categoryService.getCategoryById(categoryId);
        Category category=modelMapper.map(categoryDTO,Category.class);
        List<Post>posts=postRepo.findByCategory(category);
        return posts.stream().map(t->modelMapper.map(t,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPostByKeyword(String keyword) {
        List<Post> posts=postRepo.findByTitleContaining(keyword);
        List<PostDTO> postDTOS=posts.stream().map(t->modelMapper.map(t,PostDTO.class)).collect(Collectors.toList());

        return postDTOS;
    }
}
