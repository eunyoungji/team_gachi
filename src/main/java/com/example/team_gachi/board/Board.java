package com.example.team_gachi.board;

import com.example.team_gachi.boardUser.BoardUser;
import com.example.team_gachi.column.ColumnClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    private Long id;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ColumnClass> columnClassList;

    @OneToMany(mappedBy = "board")
    private List<BoardUser> boardUserList;

    public void addColumn(ColumnClass column){
        this.columnClassList.add(column);
    }
}
