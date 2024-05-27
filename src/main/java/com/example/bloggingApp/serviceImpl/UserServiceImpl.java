package com.example.bloggingApp.serviceImpl;

import com.example.bloggingApp.exceptions.ResourceNotFoundException;
import com.example.bloggingApp.model.User;
import com.example.bloggingApp.payload.UserDTO;
import com.example.bloggingApp.repository.UserRepo;
import com.example.bloggingApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dTOToUser(userDTO);
        User savedUser = userRepo.save(user);
        return userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        //user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        User updatedUser=userRepo.save(user);
        return userToDTO(updatedUser);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users=userRepo.findAll();
        List<UserDTO> userDTOS=users.stream().map(this::userToDTO).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        return userToDTO(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        userRepo.delete(user);
    }

    private User dTOToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);

//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());
        return user;
    }

    private UserDTO userToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}
