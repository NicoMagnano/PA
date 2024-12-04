package com.example.pa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pa.User.UserService;
import com.example.pa.controller.DTO.UserDTO.UserProfileDTO;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@RequestParam String username) {
        UserProfileDTO profile = userService.getProfile(username);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(
            @RequestParam String username,
            @RequestBody UserProfileDTO userProfileDTO) {
        userService.updateProfile(username, userProfileDTO);
        return ResponseEntity.noContent().build();
    }
}

