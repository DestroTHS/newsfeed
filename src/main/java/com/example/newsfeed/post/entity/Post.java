package com.example.newsfeed.post.entity;

import com.example.newsfeed.common.entity.BaseEntity;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
