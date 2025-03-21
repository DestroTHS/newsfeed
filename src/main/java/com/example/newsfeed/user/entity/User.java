package com.example.newsfeed.user.entity;

import com.example.newsfeed.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String nickname;

    @Column (nullable = false)
    private String password;

    public User(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public User(String email) {
        this.email = email;
    }
}
