package com.example.team_gachi.board;

import com.example.team_gachi.card.Card;
import com.example.team_gachi.column.ColumnClass;
import com.example.team_gachi.common.Timestamped;
import com.example.team_gachi.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.List;

@Getter
@Setter
@Table(name ="board")
@NoArgsConstructor
@Entity
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "boardName", nullable = false, unique = true)
    private String boardName;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "boardInfo", nullable = false, unique = true)
    private String boardInfo;

    @Column
    @Formula("(select count(*) from card i where i.board_id = id)")
    private int totalCards;

    @OneToMany(targetEntity = ColumnClass.class, mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ColumnClass> columnClasses;

    @OneToMany(targetEntity = Card.class, mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> cards;

    public Board(BoardRequestDto requestDto) {
        this.boardName = requestDto.getBoardName();
        this.nickname = requestDto.getNickname();
        this.boardInfo = requestDto.getBoardInfo();
    }


    public void addColumn(ColumnClass addOrderColumn) {
    }
}
