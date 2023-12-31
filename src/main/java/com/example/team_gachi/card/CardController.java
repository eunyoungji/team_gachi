package com.example.team_gachi.card;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // 카드 생성
    //@PostMapping("/boards/columns/{columnId}/card")
    @PostMapping("/cards")
    public ResponseEntity<CardResponseDto> createCard(@PathVariable Long columId, @RequestBody CardRequestDto cardRequestDto){
        CardResponseDto result = cardService.createCard(cardRequestDto, columId); //
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

//    // 카드 조회
//    @GetMapping("/column/{columnId}/card")
//    public List<CardResponseDto> getCards(@PathVariable Long column_id){
//        return cardService.getCards(column_id);
//    }

    // 카드 수정 --> 타이틀 따로, 내용 따로....부분적으로 수정할 수 있게??
    @PutMapping("/cards/{cardId}")
    public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long cardId, @RequestBody CardRequestDto cardRequestDto){
        CardResponseDto result = cardService.updateCard(cardId, cardRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 카드 삭제
    @DeleteMapping("/cards/{cardId}")
    public void deleteCard(@PathVariable Long cardId){
        cardService.deleteCard(cardId);
    }

//    @PatchMapping("/cards/{card_Id}/duedate")
//    public ResponseEntity<CardResponseDto> changeDueDate(@PathVariable Long id, @RequestBody CardRequestDto cardRequestDto) {
//
//        return null;
//    }

//    @PatchMapping("/cards/{card_Id}/color")
//    public ResponseEntity<CardResponseDto> changeColor(@PathVariable Long id, @RequestBody CardRequestDto cardRequestDto) {
//        return null;
//    }

}
