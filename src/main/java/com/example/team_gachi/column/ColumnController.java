package com.example.team_gachi.column;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ColumnController {
    private final ColumnService columnService;

    //칼럼 생성
    @PostMapping("/board/{id}/column")
    public ColumnResponseDto createColumn(@PathVariable Long id, @RequestBody ColumnRequestDto columnRequestDto){
        ColumnResponseDto result = columnService.createColumn(columnRequestDto, id);

        return result;
    }

    //칼럼 이름 수정
    @PutMapping("/column/{id}")
    public ColumnResponseDto updateColumn(@PathVariable Long id, @RequestBody ColumnRequestDto columnRequestDto){
        ColumnResponseDto result = columnService.updateColumn(id, columnRequestDto);

        return result;
    }

    //칼럼 삭제
    @DeleteMapping("/column/{id}")
    public void deleteColumn(@PathVariable Long id){
        columnService.deleteColumn(id);
    }

    //칼럼 순서 변경
    @PostMapping("/board/{id}")
    public List<ColumnResponseDto> orderColumn(@PathVariable Long id, @RequestBody List<Long> columnIndex){
        List<ColumnResponseDto> columnResponseDtoList = columnService.orderColumn(id, columnIndex);
        return columnResponseDtoList;
    }
}
