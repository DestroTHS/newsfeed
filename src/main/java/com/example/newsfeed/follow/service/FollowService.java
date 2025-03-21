package com.example.newsfeed.follow.service;

import com.example.newsfeed.follow.entity.Follow;
import com.example.newsfeed.follow.repository.FollowRepository;
import com.example.newsfeed.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public void followUser(User currentUser, Long followeeId, User followee) {
        if(currentUser.getId().equals(followee.getId())) {
            throw new IllegalArgumentException("자기 자신은 팔로우할 수 없습니다.");
        }
        if(followRepository.findByFollowerAndFollowee(currentUser, followee).isPresent()){
            throw new IllegalArgumentException("이미 팔로우 중입니다.");
        }
        Follow follow = Follow.builder()
                .follower(currentUser)
                .followee(followee)
                .build();
        followRepository.save(follow);
    }

    public void unfollowUser(User currentUser, User followee) {
        Follow follow = followRepository.findByFollowerAndFollowee(currentUser, followee)
                .orElseThrow(() -> new IllegalArgumentException("팔로우 관계가 존재하지 않습니다."));
        followRepository.delete(follow);
    }
}
