package com.example.userdetails.controller

import com.example.userdetails.dto.UserDTO
import com.example.userdetails.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.List

@RestController
@RequestMapping("/api/users")
class UserControllerKt(private val userService: UserService) {

    @GetMapping("/async")
    suspend fun getUsersAsync(): java.util.List<UserDTO> {
        return userService.getAllUsersAsync() as java.util.List<UserDTO>
    }

    @GetMapping("/async-coroutine")
    suspend fun getUsersAsyncCoroutine(): java.util.List<UserDTO> {
        return userService.getAllUsersAsyncCoroutine() as java.util.List<UserDTO>
    }
} 