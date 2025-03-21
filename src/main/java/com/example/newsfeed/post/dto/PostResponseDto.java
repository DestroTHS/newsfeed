package com.example.newsfeed.post.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;

    public PostResponseDto(Long id, Long userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
