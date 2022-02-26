package com.example.atmserver.controllers;

import com.example.atmserver.model.Card;
import com.example.atmserver.services.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/atm/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/authenticate")
    public Card authenticateCard(@RequestParam(name = "cardNumber") String cardNumber, @RequestParam(name = "pin") int pin) {
        Card card = cardService.authenticateCard(cardNumber, pin);

        if (card == null) {
            try {
                throw new Exception("Карта не найдена");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return card;
    }

    @PutMapping("/{cardId}/changePin")
    public String changePin(@PathVariable int cardId, @RequestParam(name = "newPin") int newPin) {

        if (newPin < 1000 || newPin > 9999) {
            return "Не допустимый pin";
        }

        int rows = cardService.updateCardPin(cardId, newPin);

        if (rows != 1) {
            try {
                throw new Exception("Ошибка при изменении pin");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Pin код изменен";
    }
}
