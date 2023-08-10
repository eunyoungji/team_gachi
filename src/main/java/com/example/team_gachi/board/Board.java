package com.example.team_gachi.board;

import com.example.team_gachi.common.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
@Table(name ="board")
@NoArgsConstructor
@Entity
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "boardName", nullable = false, unique = true)
    private String boardName;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "boardInfo", nullable = false, unique = true)
    private String boardInfo;

    public Board(BoardRequestDto requestDto) {
        this.boardName = requestDto.getBoardName();
        this.nickname = requestDto.getNickname();
        this.boardInfo = requestDto.getBoardInfo();
    }


}
