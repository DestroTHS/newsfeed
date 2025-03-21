package com.example.newsfeed.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponseDto {

    private final Long userId;

    public AuthLoginResponseDto(Long userId) {
        this.userId = userId;
    }
}
