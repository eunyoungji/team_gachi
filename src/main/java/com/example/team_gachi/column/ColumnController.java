package com.example.team_gachi.column;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ColumnController {
    private final ColumnService columnService;

    //칼럼 생성
    @PostMapping("/board/{id}/column")
    public ColumnResponseDto createColumn(@PathVariable Long id, @RequestBody ColumnRequestDto columnRequestDto){
        System.out.println("실행확인1");
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
}
