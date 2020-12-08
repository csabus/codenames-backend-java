package hu.idevelopment.codenames.repository.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "game")
public class DbGame {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    @Type(type="uuid-char")
    private UUID id;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "started")
    private LocalDateTime started;

    @Column(name = "finished")
    private LocalDateTime finished;

    @OneToMany(mappedBy = "game")
    private List<DbPlayer> playerList = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<DbPlayer> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<DbPlayer> playerList) {
        this.playerList = playerList;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = finished;
    }
}
