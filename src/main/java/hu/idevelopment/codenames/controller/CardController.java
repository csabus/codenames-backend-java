package hu.idevelopment.codenames.controller;

import hu.idevelopment.codenames.domain.Card;
import hu.idevelopment.codenames.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping()
    public List<Card> getCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/random")
    public List<Card> getRandomCards(){
        return cardService.getRandomCards(25);
    }
}
