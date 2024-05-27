package com.example.bloggingApp.service;

import com.example.bloggingApp.payload.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, Integer userId);

    List<UserDTO> getAllUser();

    UserDTO getUserById(Integer userId);

    void deleteUser(Integer userId);

}
