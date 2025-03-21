package com.example.newsfeed.follow.controller;

import com.example.newsfeed.follow.service.FollowService;
import com.example.newsfeed.post.service.PostService;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserRepository userRepository;
    private final PostService postService;

    @PostMapping("/{followeeId}")
    public ResponseEntity<String> followUser(User currentUser, @PathVariable Long followeeId) {
        if(currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return userRepository.findById(followeeId)
                .map(followee -> {
                    try {
                        followService.followUser(currentUser, followeeId, followee);
                        return ResponseEntity.ok("팔로우 완료");
                    } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                    }
                }).orElseGet(() -> ResponseEntity.badRequest().body("팔로우 대상 사용자를 찾을 수 없습니다."));
    }

    @DeleteMapping("/{followeeId}")
    public ResponseEntity<String> unfollowUser(User currentUser, @PathVariable Long followeeId) {
        if(currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return userRepository.findById(followeeId)
                .map(followee -> {
                    try {
                        followService.unfollowUser(currentUser, followee);
                        return ResponseEntity.ok("언팔로우 완료");
                    } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                    }
                }).orElseGet(() -> ResponseEntity.badRequest().body("대상 사용자를 찾을 수 없습니다."));
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getFollowedPosts(User currentUser,
                                              @RequestParam(defaultValue = "0") int page) {
        if(currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다.");
        }
        return ResponseEntity.ok(postService.getFollowedNewsfeed(currentUser, page));
    }
}
