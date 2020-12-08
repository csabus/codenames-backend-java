package hu.idevelopment.codenames.service;

import hu.idevelopment.codenames.domain.Game;
import hu.idevelopment.codenames.domain.Player;
import hu.idevelopment.codenames.repository.GameRepository;
import hu.idevelopment.codenames.repository.entities.DbGame;
import hu.idevelopment.codenames.repository.entities.DbPlayer;
import hu.idevelopment.codenames.repository.mapping.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class GameService {
    private final GameRepository gameRepository;
    private final DbMapper mapper;
    private final PlayerService playerService;

    @Autowired
    public GameService(GameRepository gameRepository,
                       DbMapper mapper,
                       PlayerService playerService){
        this.gameRepository = gameRepository;
        this.mapper = mapper;
        this.playerService = playerService;
    }

    public Game newGame(){
        DbGame dbGame = new DbGame();
        gameRepository.save(dbGame);
        Game game = mapper.map(dbGame);
        return game;
    }

    public Game getGame(String gameId) {
        DbGame dbGame = gameRepository.getOne(UUID.fromString(gameId));
        return mapper.map(dbGame);
    }

}
