package hu.idevelopment.codenames.service;

import hu.idevelopment.codenames.domain.Card;
import hu.idevelopment.codenames.repository.CardRepository;
import hu.idevelopment.codenames.repository.entities.DbCard;
import hu.idevelopment.codenames.repository.mapping.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final DbMapper mapper;

    @Autowired
    public CardService(CardRepository cardRepository, DbMapper mapper) {
        this.cardRepository = cardRepository;
        this.mapper = mapper;
    }

    public List<Card> getAllCards(){
        List<DbCard> cardList = cardRepository.findAll();
        return cardList.stream().map(mapper::map).collect(Collectors.toList());
    }

    public List<Card> getRandomCards(int count){
        List<DbCard> cardList = cardRepository.findAll();
        Collections.shuffle(cardList);
        return cardList.subList(0, count).stream().map(mapper::map).collect(Collectors.toList());
    }
}
