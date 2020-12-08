package hu.idevelopment.codenames.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private UUID id;
    //private List<GameCard> cardList = new ArrayList<>();
    private Player redSpyMaster;
    private Player blueSpyMaster;
    private List<Player> redOperativeList = new ArrayList<>();
    private List<Player> blueOperativeList = new ArrayList<>();
    private List<Player> unJoinedPlayerList = new ArrayList<>();
    private Player owner;
    private Long created = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    private Long started;
    private Long finished;

    public Game() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GameState getState() {
        if(started == null) {
            return GameState.NOT_STARTED;
        } else if(finished == null) {
            return GameState.RUNNING;
        } else {
            return GameState.FINISHED;
        }
    }

    /*public List<GameCard> getCardList() {
        return cardList;
    }*/

    /*public void setCardList(List<GameCard> cardList) {
        this.cardList = cardList;
    }*/

    public Player getRedSpyMaster() {
        return redSpyMaster;
    }

    public void setRedSpyMaster(Player redSpyMaster) {
        this.redSpyMaster = redSpyMaster;
    }

    public Player getBlueSpyMaster() {
        return blueSpyMaster;
    }

    public void setBlueSpyMaster(Player blueSpyMaster) {
        this.blueSpyMaster = blueSpyMaster;
    }

    public List<Player> getRedOperativeList() {
        return redOperativeList;
    }

    public void setRedOperativeList(List<Player> redOperativeList) {
        this.redOperativeList = redOperativeList;
    }

    public List<Player> getBlueOperativeList() {
        return blueOperativeList;
    }

    public void setBlueOperativeList(List<Player> blueOperativeList) {
        this.blueOperativeList = blueOperativeList;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getStarted() {
        return started;
    }

    public void setStarted(Long started) {
        this.started = started;
    }

    public Long getFinished() {
        return finished;
    }

    public void setFinished(Long finished) {
        this.finished = finished;
    }

    public List<Player> getUnJoinedPlayerList() {
        return unJoinedPlayerList;
    }

    public void setUnJoinedPlayerList(List<Player> unJoinedPlayerList) {
        this.unJoinedPlayerList = unJoinedPlayerList;
    }
}
