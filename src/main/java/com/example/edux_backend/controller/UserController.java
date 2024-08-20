package com.example.edux_backend.controller;

import com.example.edux_backend.dto.response.ApiResponse;
import com.example.edux_backend.dto.request.UserCreationRequest;
import com.example.edux_backend.dto.request.UserUpdateRequest;
import com.example.edux_backend.dto.response.UserResponse;
import com.example.edux_backend.entity.User;
import com.example.edux_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping( "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@Valid @RequestBody UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();    }

    @GetMapping("/{userName}")
    public List<UserResponse> getUserByName(@PathVariable("userName") String userName) {
        return userService.getUserByName(userName);
    }

    @GetMapping("/id/{userId}")
    public User getUserById(@PathVariable("userId") Long id){
        return (User) userService.getUserById(id);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUserById(userId);
        return "User has been completed!";
    }
}

