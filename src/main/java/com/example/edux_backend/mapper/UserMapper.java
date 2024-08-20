package com.example.edux_backend.mapper;

import com.example.edux_backend.dto.request.UserCreationRequest;
import com.example.edux_backend.dto.request.UserUpdateRequest;
import com.example.edux_backend.dto.response.UserResponse;
import com.example.edux_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(source = "", target = "")
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
