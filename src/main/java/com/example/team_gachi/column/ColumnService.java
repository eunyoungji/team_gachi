package com.example.team_gachi.column;

import com.example.team_gachi.board.Board;
import com.example.team_gachi.board.BoardRepository;
import com.example.team_gachi.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardService boardService;

    public ColumnResponseDto createColumn(ColumnRequestDto columnRequestDto, Long id){
        Board board = boardService.findBoard(id);
        ColumnClass column = new ColumnClass(columnRequestDto, board);

        ColumnClass saveColumn = columnRepository.save(column);

        //순서 번호 저장
        saveColumn.setOrder();
        ColumnClass addOrderColumn = columnRepository.save(saveColumn);
        //board entity에 있는 columnClassList에 추가
        board.addColumn(addOrderColumn);

        ColumnResponseDto columnResponseDto = new ColumnResponseDto(addOrderColumn);
        return columnResponseDto;
    }

    @Transactional
    public ColumnResponseDto updateColumn(Long id, ColumnRequestDto columnRequestDto){
        ColumnClass column = findColumnClass(id);

        column.setTitle(columnRequestDto.getTitle());

        return new ColumnResponseDto(column);
    }

    public void deleteColumn(Long id){
        ColumnClass column = findColumnClass(id);

        columnRepository.delete(column);
    }

    public ColumnClass findColumnClass(Long id){
        return columnRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 칼럼이 존재하지 않습니다."));
    }
}
