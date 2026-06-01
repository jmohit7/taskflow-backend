package com.task.service;

import com.task.dto.UserRequestDto;
import com.task.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUser();
    UserResponseDto fetchUser(String id);
    String deleteUser(String id);
    UserResponseDto updateUser(String id, UserRequestDto userRequestDto);
}
