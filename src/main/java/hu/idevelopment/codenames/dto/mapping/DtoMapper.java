package hu.idevelopment.codenames.dto.mapping;

import hu.idevelopment.codenames.domain.Color;
import hu.idevelopment.codenames.domain.Player;
import hu.idevelopment.codenames.domain.PlayerRole;
import hu.idevelopment.codenames.dto.JoinGameForm;
import hu.idevelopment.codenames.dto.NewGameForm;
import hu.idevelopment.codenames.dto.PlayerDetails;
import hu.idevelopment.codenames.dto.PlayerForm;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DtoMapper {
    public Player map(NewGameForm newGameForm) {
        Player player = new Player();
        player.setName(newGameForm.getPlayerName());
        player.setOwner(newGameForm.isOwner());
        return player;
    }

    public Player map(PlayerForm playerForm) {
        Player player = new Player();
        player.setName(playerForm.getName());
        if(playerForm.getGameId() != null) {
            player.setGameId(UUID.fromString(playerForm.getGameId()));
        }
        player.setOwner(player.isOwner());
        try{
            player.setRole(PlayerRole.valueOf(playerForm.getRole()));
            player.setColor(Color.valueOf(playerForm.getColor()));
        } catch (Exception ignored){}
        return player;
    }

    public Player map(JoinGameForm joinGameForm) {
        Player player = new Player();
        player.setId(UUID.fromString(joinGameForm.getPlayerId()));
        try{
            player.setRole(PlayerRole.valueOf(joinGameForm.getRole()));
            player.setColor(Color.valueOf(joinGameForm.getColor()));
        } catch (Exception ignored){}
        return player;
    }

    public PlayerDetails map(Player player) {
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setId(player.getId().toString());
        playerDetails.setGameId(player.getGameId().toString());
        playerDetails.setName(player.getName());
        playerDetails.setOwner(player.isOwner());
        if(player.getRole() != null) {
            playerDetails.setRole(player.getRole().toString());
        }
        if(player.getColor() != null) {
            playerDetails.setColor(player.getColor().toString());
        }
        return  playerDetails;
    }
}
