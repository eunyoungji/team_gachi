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

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String email;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
