package hu.idevelopment.codenames.dto;

public class NewGameForm {
    private String playerName;
    private boolean isOwner = true;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
