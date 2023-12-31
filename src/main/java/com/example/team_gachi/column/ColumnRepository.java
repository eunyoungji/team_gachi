package com.example.team_gachi.column;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnRepository extends JpaRepository<ColumnClass, Long> {
    List<ColumnClass> findByBoardId(Long boardId);
}
