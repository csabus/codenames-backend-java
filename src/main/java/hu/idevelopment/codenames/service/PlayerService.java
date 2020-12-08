package hu.idevelopment.codenames.service;

import hu.idevelopment.codenames.domain.Color;
import hu.idevelopment.codenames.domain.Game;
import hu.idevelopment.codenames.domain.Player;
import hu.idevelopment.codenames.domain.PlayerRole;
import hu.idevelopment.codenames.repository.GameRepository;
import hu.idevelopment.codenames.repository.PlayerRepository;
import hu.idevelopment.codenames.repository.entities.DbGame;
import hu.idevelopment.codenames.repository.entities.DbPlayer;
import hu.idevelopment.codenames.repository.mapping.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final DbMapper mapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, GameRepository gameRepository, DbMapper mapper){
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.mapper = mapper;
    }

    public Player newPlayer(Player player){
        DbPlayer dbPlayer = mapper.map(player);
        playerRepository.save(dbPlayer);

        return mapper.map(dbPlayer);
    }

    public Player getPlayerById(String playerId) {
        DbPlayer dbPlayer = playerRepository.getOne(UUID.fromString(playerId));
        return mapper.map(dbPlayer);
    }

    public Player joinToGame(Player player, String gameId) {
        DbGame dbGame = gameRepository.getOne(UUID.fromString(gameId));
        DbPlayer dbPlayer = playerRepository.getOne(player.getId());
        if(playerCanJoin(dbPlayer, dbGame, player.getRole(), player.getColor())) {
            if(player.getRole() != null) {
                dbPlayer.setRole(player.getRole().toString());
            }
            if(player.getColor() != null) {
                dbPlayer.setColor(player.getColor().toString());
            }
            dbPlayer.setGame(dbGame);
            playerRepository.save(dbPlayer);
            return mapper.map(dbPlayer);
        } else {
            return null;
        }
    }

    private boolean playerCanJoin(DbPlayer dbPlayer, DbGame dbGame, PlayerRole role, Color color) {
        if(dbPlayer.getColor() != null || dbPlayer.getRole() != null) {
            return false;
        }
        if(role == PlayerRole.SPY_MASTER) {
            for (DbPlayer player : dbGame.getPlayerList()) {
                if((PlayerRole.SPY_MASTER.toString().equals(player.getRole()))
                        && color.toString().equals(player.getColor())) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public Player updatePlayer(Player player)
    {
        DbPlayer dbPlayer = playerRepository.getOne(player.getId());
        if(player.getColor() != null) {
            dbPlayer.setColor(player.getColor().toString());
        }
        if(player.getRole() != null) {
            dbPlayer.setRole(player.getRole().toString());
        }
        return mapper.map(dbPlayer);
    }*/
}
