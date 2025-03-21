package com.example.newsfeed.follow.repository;

import com.example.newsfeed.follow.entity.Follow;
import com.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowee(User follower, User followee);
    List<Follow> findByFollower(User follower);
}