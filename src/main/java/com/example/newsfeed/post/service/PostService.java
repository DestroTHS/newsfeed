package com.example.newsfeed.post.service;

import com.example.newsfeed.post.dto.CreatePostRequestDto;
import com.example.newsfeed.post.dto.PostResponseDto;
import com.example.newsfeed.post.dto.UpdatePostRequestDto;
import com.example.newsfeed.post.entity.Post;
import com.example.newsfeed.post.repository.PostRepository;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto save(CreatePostRequestDto dto) {
        Post post = new Post(dto.getTitle(), dto.getContent());
        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost.getId(), savedPost.getUser().getId(), savedPost.getTitle(), savedPost.getContent());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> responseDto = new ArrayList<>();
        for (Post post : posts) {
            PostResponseDto dto = new PostResponseDto(post.getId(), post.getUser().getId(), post.getTitle(), post.getContent());
            responseDto.add(dto);
        }
        return responseDto;
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 id")
        );
        return new PostResponseDto(post.getId(), post.getUser().getId(), post.getTitle(), post.getContent());
    }

    @Transactional
    public PostResponseDto update(Long id, UpdatePostRequestDto dto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 id")
        );
        post.update(dto.getTitle(), dto.getContent());

        return new PostResponseDto(post.getId(), post.getUser().getId(), post.getTitle(), post.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
        postRepository.deleteById(id);
    }
}
