package com.example.userdetails.service;

import com.example.userdetails.dto.UserDTO;
import com.example.userdetails.mapper.UserMapper;
import com.example.userdetails.model.User;
import com.example.userdetails.repository.UserRepository;
import com.example.userdetails.util.CoroutineUtils;
import kotlin.coroutines.Continuation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CoroutineUtils coroutineUtils;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, CoroutineUtils coroutineUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.coroutineUtils = coroutineUtils;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersAsyncCoroutine() {
        return coroutineUtils.withIOContext(() -> {
            List<UserDTO> users = userRepository.findAll().stream()
                    .map(userMapper::toDTO)
                    .collect(Collectors.toList());
            return users;
        });
    }

    public List<UserDTO> getAllUsersAsync() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) {
            return Optional.empty();
        }
        
        userDTO.setId(id);
        User user = userMapper.toEntity(userDTO);
        User updatedUser = userRepository.save(user);
        return Optional.of(userMapper.toDTO(updatedUser));
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        
        userRepository.deleteById(id);
        return true;
    }
}
