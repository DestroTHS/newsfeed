package com.example.newsfeed.user.dto;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

    private final Long id;
    private final String email;
    private final String nickname;

    public UserProfileResponseDto(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserProfileResponseDto userDto(User user) {
        return new UserProfileResponseDto(user.getId(), user.getEmail(), user.getNickname());
    }
}
