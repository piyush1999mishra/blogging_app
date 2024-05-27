package com.example.bloggingApp.serviceImpl;

import com.example.bloggingApp.exceptions.ResourceNotFoundException;
import com.example.bloggingApp.model.Comment;
import com.example.bloggingApp.model.Post;
import com.example.bloggingApp.payload.CommentDTO;
import com.example.bloggingApp.repository.CommentRepo;
import com.example.bloggingApp.repository.PostRepo;
import com.example.bloggingApp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment=modelMapper.map(commentDTO,Comment.class);
        comment.setPost(post);
        Comment savedComment=commentRepo.save(comment);
        return modelMapper.map(savedComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        commentRepo.delete(comment);
    }
}
