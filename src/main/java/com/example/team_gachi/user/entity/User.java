package com.example.team_gachi.user.entity;

import com.example.team_gachi.boardUser.BoardUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<BoardUser> boardUserList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public User(String username, String password, String nickname, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;

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