package com.example.team_gachi.card;

import com.example.team_gachi.column.ColumnClass;
import com.example.team_gachi.column.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnService columnService;

    // 카드 생성
    public CardResponseDto createCard(CardRequestDto cardRequestDto, Long columnId) { //Long columId
        ColumnClass column = columnService.findColumnClass(columnId);
        Card card = new Card(cardRequestDto, column); // column

        Card saveCard = cardRepository.save(card);

        saveCard.setIndex();
        Card addOrderCard = cardRepository.save(saveCard);

        column.addCard(addOrderCard);

        CardResponseDto cardResponseDto = new CardResponseDto(addOrderCard);
        return cardResponseDto;
    }

//    //  카드 전체 조회
//    public List<CardResponseDto> getCards(Long column_id) {
//        return cardRepository.findById(column_id).stream().map(CardResponseDto::new).toList();
//    }

    //  카드 수정
    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto cardRequestDto) {
        Card card = findCard(id);
        card.updateCard(cardRequestDto);
        return new CardResponseDto(card);
    }

    //  카드 삭제
    public void deleteCard(Long id) {
        Card card = findCard(id);
        cardRepository.delete(card);

    }

    private Card findCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 카드는 존재하지 않습니다.")
        );
    }

}
