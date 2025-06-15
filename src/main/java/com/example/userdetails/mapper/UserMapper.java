package com.example.userdetails.mapper;

import com.example.userdetails.dto.UserDTO;
import com.example.userdetails.model.User;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class UserMapper {
    
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getDateOfBirth(),
            user.getPhoneNumber(),
            user.getAddress()
        );
    }
    
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new User(
            dto.getId(),
            dto.getUsername(),
            dto.getEmail(),
            dto.getFirstName(),
            dto.getLastName(),
            dto.getDateOfBirth(),
            dto.getPhoneNumber(),
            dto.getAddress()
        );
    }
}