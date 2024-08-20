package com.example.edux_backend.service;

import com.example.edux_backend.dto.request.UserCreationRequest;
import com.example.edux_backend.dto.request.UserUpdateRequest;
import com.example.edux_backend.dto.response.UserResponse;
import com.example.edux_backend.entity.User;
import com.example.edux_backend.exception.AppException;
import com.example.edux_backend.exception.ErrorCode;
import com.example.edux_backend.mapper.UserMapper;
import com.example.edux_backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public User createUser(@RequestBody UserCreationRequest request){
        if(userRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXITED);
        }
        User user = userMapper.toUser(request);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        return userRepository.save(user);
    }

    public List<UserResponse> getByUserName(String name) {
        Optional<User> optionalUser = userRepository.findByUserName(name);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("No users found with name: " + name);
        }
        // Chuyển đổi User thành UserResponse và đóng gói vào danh sách
        User user = optionalUser.get();
        return Collections.singletonList(userMapper.toUserResponse(user));
    }

    public List<UserResponse> toUserResponse(Optional<User> users) {
        return users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse updateUser(Long userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: "+ userId));
        userMapper.updateUser(user, request);
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public List<UserResponse> getUserByName(String name) {
        List<User> user = userRepository.findByFirstname(name);
        if (user.isEmpty()) {
            throw new RuntimeException("No users found with name: " + name);
        }
        return user.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getUserByType(String type){
        List<User> user = userRepository.findByTypeOfUser(type);
        if (user.isEmpty()) {
            throw new RuntimeException("No users have type: "+ type);
        }
        return user.stream()
                .map(userMapper::toUserResponse) // Chuyển đổi từng User thành UserResponse
                .collect(Collectors.toList());
    }
}
