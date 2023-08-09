package com.example.team_gachi.card;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // 카드 생성
    @PostMapping("/column/{column_id}/card")
    public CardResponseDto createCard(@PathVariable Long id, @RequestBody CardRequestDto cardRequestDto){
        CardResponseDto result = cardService.createCard(cardRequestDto, id);
        return result;
    }

//    // 카드 조회
//    @GetMapping("/column/{column_id}/card")
//    public List<CardResponseDto> getCards(@PathVariable Long column_id){
//        return cardService.getCards(column_id);
//    }

    // 카드 수정
    @PutMapping("/card/{card_id}")
    public CardResponseDto updateCard(@PathVariable Long id, @RequestBody CardRequestDto cardRequestDto){
        CardResponseDto result = cardService.updateCard(id, cardRequestDto);
        return result;
    }

    // 카드 삭제
    @DeleteMapping("/card/{card_id}")
    public void deleteCard(@PathVariable Long id){
        cardService.deleteCard(id);
    }
}
