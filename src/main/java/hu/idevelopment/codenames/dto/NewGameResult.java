package hu.idevelopment.codenames.dto;

import java.util.UUID;

public class NewGameResult {
    private String gameId;
    private String playerId;

    public NewGameResult() {
    }

    public NewGameResult(UUID gameId, UUID playerId) {
        this.gameId = gameId.toString();
        this.playerId = playerId.toString();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
