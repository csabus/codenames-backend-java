package hu.idevelopment.codenames.controller;

import hu.idevelopment.codenames.domain.Game;
import hu.idevelopment.codenames.domain.Player;
import hu.idevelopment.codenames.dto.JoinGameForm;
import hu.idevelopment.codenames.dto.NewGameForm;
import hu.idevelopment.codenames.dto.NewGameResult;
import hu.idevelopment.codenames.dto.mapping.DtoMapper;
import hu.idevelopment.codenames.service.GameService;
import hu.idevelopment.codenames.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;
    private final DtoMapper mapper;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, DtoMapper mapper){
        this.gameService = gameService;
        this.playerService = playerService;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity<NewGameResult> newGame(@RequestBody NewGameForm newGameForm){
        Game game = gameService.newGame();

        Player player = mapper.map(newGameForm);
        player = playerService.newPlayer(player);
        playerService.joinToGame(player, game.getId().toString());

        NewGameResult result = new NewGameResult(game.getId(), player.getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameDetails(@PathVariable("gameId") String gameId) {
        try{
            Game game = gameService.getGame(gameId);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/join/{gameId}")
    public ResponseEntity<Player> joinGame(@PathVariable("gameId") String gameId, @RequestBody JoinGameForm joinGameForm){
        try {
            Player player = mapper.map(joinGameForm);
            player = playerService.joinToGame(player, gameId);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
