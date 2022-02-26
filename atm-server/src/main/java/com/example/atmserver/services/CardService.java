package com.example.atmserver.services;

import com.example.atmserver.model.Card;
import com.example.atmserver.repositories.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card authenticateCard(String cardNumber, int pin) {
        return cardRepository.getCardByCardNumberAndPin(cardNumber, pin);
    }

    public int updateCardPin(int cardId, int pin) {
        return cardRepository.updateCardPin(cardId, pin);
    }
}
