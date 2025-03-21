package com.example.newsfeed.user.controller;

import com.example.newsfeed.user.dto.UserProfileResponseDto;
import com.example.newsfeed.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserProfileResponseDto>> findAll() {
        List<UserProfileResponseDto> userProfileResponseDtoList = userService.findAll();
        return new ResponseEntity<>(userProfileResponseDtoList, HttpStatus.OK);
    }
}
