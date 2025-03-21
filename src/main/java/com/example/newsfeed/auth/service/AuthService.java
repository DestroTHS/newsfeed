package com.example.newsfeed.auth.service;

import com.example.newsfeed.auth.dto.AuthLoginResponseDto;
import com.example.newsfeed.auth.dto.AuthLoginRequestDto;
import com.example.newsfeed.auth.dto.AuthSignupRequestDto;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        User user = new User(dto.getEmail());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        User user = (User) userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원"));
        return new AuthLoginResponseDto(user.getId());
    }
}
