package com.example.newsfeed.user.service;

import com.example.newsfeed.user.dto.UserProfileResponseDto;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public List<UserProfileResponseDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(UserProfileResponseDto::userDto)
                .toList();
    }
}