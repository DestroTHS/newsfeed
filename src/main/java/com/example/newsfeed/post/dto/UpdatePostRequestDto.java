package com.example.newsfeed.post.dto;

import lombok.Getter;

@Getter
public class UpdatePostRequestDto {

    private final String title;
    private final String content;

    public UpdatePostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
