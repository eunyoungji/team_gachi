package com.example.team_gachi.user.entity;

import com.example.team_gachi.dto.UserProfileRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private Long id;

    private String userid;

    // username , 중복방지
    @Column(nullable = false, unique = true)
    private String username;

    // 비밀번호, 중복방지
    @Column(nullable = false, unique = true)
    private String password;

    // 닉네임, 중복방지
    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


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

    public String getUserId() {
        return userid;
    }
}

    /* 프로필 수정
    public void modifyProfile(UserProfileRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.nickname = requestDto.getNickname();
    }

}
*/