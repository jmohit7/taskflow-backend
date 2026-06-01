package com.task.service.impl;

import com.task.dto.UserRequestDto;
import com.task.dto.UserResponseDto;
import com.task.entity.User;
import com.task.exception.UserAlreadyExistsException;
import com.task.exception.UserNotFoundException;
import com.task.repository.UserRepository;
import com.task.service.UserService;
import com.task.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = mapToUser(userRequestDto);
        User savedUser = userRepository.save(user);

        return mapToUserResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto fetchUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return mapToUserResponseDto(user);
    }

    @Override
    public String deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return "User removed successfully!";
    }

    @Override
    public UserResponseDto updateUser(String id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        User updatedUser = userRepository.save(user);

        return mapToUserResponseDto(updatedUser);
    }

    private User mapToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        user.setPassword(userRequestDto.getPassword());
        user.setRole(Role.USER);

        String id = generateId();

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User", "email", userRequestDto.getEmail());
        }

        while (userRepository.existsById(id)) {
            id = generateId();
        }

        user.setId(id);

        return user;
    }

    private UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setRole(user.getRole());

        return userResponseDto;
    }

    private String generateId() {
        long id = 1_000_000_000L + RANDOM.nextLong(9_000_000_000L);
        return String.valueOf(id);
    }
}
