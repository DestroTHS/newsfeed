package com.example.newsfeed.post.repository;

import com.example.newsfeed.post.entity.Post;
import com.example.newsfeed.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Post> findByAuthorInOrderByCreatedAtDesc(List<User> authors, Pageable pageable);

    Page<Post> findAllByOrderByUpdatedAtDesc(Pageable pageable);

    Page<Post> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
