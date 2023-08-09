package com.example.team_gachi.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    private Long id;

    // username , 중복방지
    @Column(nullable = false, unique = true)
    private String username;

    // 비밀번호, 중복방지
    @Column(nullable = false, unique = true)
    private String password;

    // 닉네임, 중복방지
    @Column(nullable = false, unique = true)
    private String nickname;

  /*
    // + 요소
    // 이메일, 중복방지
    @Column(nullable = false, unique = true)
    private String email;

   */

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    // 사용자 등록

}
