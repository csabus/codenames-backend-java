package hu.idevelopment.codenames.controller;

import hu.idevelopment.codenames.domain.Player;
import hu.idevelopment.codenames.dto.PlayerDetails;
import hu.idevelopment.codenames.dto.PlayerForm;
import hu.idevelopment.codenames.dto.mapping.DtoMapper;
import hu.idevelopment.codenames.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;
    private final DtoMapper mapper;

    @Autowired
    public PlayerController(PlayerService playerService, DtoMapper mapper){
        this.playerService = playerService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDetails> getPlayer(@PathVariable("id") String id) {
        try {
            Player player = playerService.getPlayerById(id);
            PlayerDetails playerDetails = mapper.map(player);
            return new ResponseEntity<>(playerDetails, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public Player newPlayer(@RequestBody PlayerForm playerForm){
        Player player = mapper.map(playerForm);
        player = playerService.newPlayer(player);
        player = playerService.joinToGame(player, playerForm.getGameId());
        return player;
    }
}
