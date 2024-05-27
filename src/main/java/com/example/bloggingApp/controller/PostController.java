package com.example.bloggingApp.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.example.bloggingApp.payload.PostDTO;
import com.example.bloggingApp.serviceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDTO createdPostDTO= postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(createdPostDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
        List<PostDTO> postDTOS=postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDTO> postDTOS=postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostDTO>> getAllPost(
            @RequestParam(value = "pageSize",defaultValue = "3",required = false) Integer pageSize,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value= "sortBy",defaultValue = "postId",required = false) String sortBy
    ){
        List<PostDTO> postDTOS=postService.getAllPost(pageSize,pageNumber,sortBy);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        PostDTO postDTO=postService.getPostById(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Integer postId,@RequestBody PostDTO postDTO){
        PostDTO updatedPost=postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping("/post/search/{keyword}")
    public  ResponseEntity<List<PostDTO>> search(@PathVariable("keyword") String keyword){
        List<PostDTO> postDTOS=postService.searchPostByKeyword(keyword);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

}
