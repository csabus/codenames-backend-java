package hu.idevelopment.codenames.repository.mapping;

import hu.idevelopment.codenames.domain.*;
import hu.idevelopment.codenames.repository.entities.DbCard;
import hu.idevelopment.codenames.repository.entities.DbGame;
import hu.idevelopment.codenames.repository.entities.DbPlayer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class DbMapper {
    public Card map(DbCard dbCard) {
        Card card = new Card();
        card.setId(dbCard.getId());
        card.setWord(dbCard.getWord());
        return card;
    }

    public DbPlayer map(Player player) {
        DbPlayer dbPlayer = new DbPlayer();
        dbPlayer.setId(player.getId());
        dbPlayer.setName(player.getName());
        if(player.getRole() != null) {
            dbPlayer.setRole(player.getRole().toString());
        }
        if(player.getColor() != null) {
            dbPlayer.setColor(player.getColor().toString());
        }
        dbPlayer.setOwner(player.isOwner());
        return dbPlayer;
    }

    public Player map(DbPlayer dbPlayer) {
        Player player = new Player();
        player.setId(dbPlayer.getId());
        player.setName(dbPlayer.getName());
        player.setOwner(dbPlayer.isOwner());
        if(dbPlayer.getGame() != null) {
            player.setGameId(dbPlayer.getGame().getId());
        }
        try{
            player.setRole(PlayerRole.valueOf(dbPlayer.getRole()));
            player.setColor(Color.valueOf(dbPlayer.getColor()));
        } catch (Exception ignored){}
        return player;
    }

    public Game map(DbGame dbGame){
        Game game = new Game();
        game.setId(dbGame.getId());
        game.setCreated(dbGame.getCreated().toEpochSecond(ZoneOffset.UTC));
        if(dbGame.getStarted() != null) {
            game.setStarted(dbGame.getStarted().toEpochSecond(ZoneOffset.UTC));
        }
        if(dbGame.getFinished() != null) {
            game.setFinished(dbGame.getFinished().toEpochSecond(ZoneOffset.UTC));
        }
        for (DbPlayer dbPlayer : dbGame.getPlayerList()) {
            if(dbPlayer.isOwner()) {
                game.setOwner(this.map(dbPlayer));
            }
            if(PlayerRole.SPY_MASTER.toString().equals(dbPlayer.getRole())) {
                if(Color.RED.toString().equals(dbPlayer.getColor())) {
                    game.setRedSpyMaster(this.map(dbPlayer));
                } else {
                    game.setBlueSpyMaster(this.map(dbPlayer));
                }
            } else {
                if(Color.RED.toString().equals(dbPlayer.getColor())) {
                    game.getRedOperativeList().add(this.map(dbPlayer));
                } else if(Color.BLUE.toString().equals(dbPlayer.getColor())) {
                    game.getBlueOperativeList().add(this.map(dbPlayer));
                } else {
                    game.getUnJoinedPlayerList().add(this.map(dbPlayer));
                }
            }
        }
        return game;
    }

    /*public DbGame map(Game game){
        DbGame dbGame = new DbGame();
        dbGame.setId(game.getId());
        dbGame.setCreated(LocalDateTime.ofEpochSecond(game.getCreated(), 0, ZoneOffset.UTC));
        if(game.getStarted() != null) {
            dbGame.setStarted(LocalDateTime.ofEpochSecond(game.getStarted(), 0, ZoneOffset.UTC));
        }
        if(game.getFinished() != null) {
            dbGame.setFinished(LocalDateTime.ofEpochSecond(game.getFinished(), 0, ZoneOffset.UTC));
        }
        return dbGame;
    }*/
}
