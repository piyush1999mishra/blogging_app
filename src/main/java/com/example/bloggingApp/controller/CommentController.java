package com.example.bloggingApp.controller;

import com.example.bloggingApp.payload.CommentDTO;
import com.example.bloggingApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable("postId") Integer postId){
        CommentDTO createdComment =commentService.createComment(commentDTO,postId);
        return  new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return  new ResponseEntity<>("deleted",HttpStatus.OK);

    }
}
