package com.example.bloggingApp.payload;

import com.example.bloggingApp.model.Category;
import com.example.bloggingApp.model.User;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Integer id;
    private String title;

    private String content;
    private UserDTO user;
    private CategoryDTO category;
    private List<CommentDTO> comments;
}
