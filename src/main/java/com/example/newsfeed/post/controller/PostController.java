package com.example.newsfeed.post.controller;

import com.example.newsfeed.post.dto.CreatePostRequestDto;
import com.example.newsfeed.post.dto.PostResponseDto;
import com.example.newsfeed.post.dto.UpdatePostRequestDto;
import com.example.newsfeed.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> save(@RequestBody CreatePostRequestDto dto) {
        return ResponseEntity.ok(postService.save(dto));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> update(
            @PathVariable Long id,
            @RequestBody UpdatePostRequestDto dto
    ) {
        return ResponseEntity.ok(postService.update(id, dto));
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable Long id) {
        postService.deleteById(id);
    }
}
