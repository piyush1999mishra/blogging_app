package com.example.bloggingApp.service;

import com.example.bloggingApp.payload.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO,Integer postId);
    void deleteComment(Integer commentId);
}
