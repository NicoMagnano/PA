package com.example.pa.User;

import org.springframework.stereotype.Service;

import com.example.pa.controller.DTO.UserDTO.UserProfileDTO;
import com.example.pa.controller.Mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileDTO getProfile(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return UserMapper.INSTANCE.toUserProfileDTO(user);
    }

    
    public void updateProfile(String username, UserProfileDTO userProfileDTO) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserMapper.INSTANCE.toUser(userProfileDTO, user);

        userRepository.save(user);
    }
}

