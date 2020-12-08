package hu.idevelopment.codenames.repository;

import hu.idevelopment.codenames.repository.entities.DbPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<DbPlayer, UUID> {
}
