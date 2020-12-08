package hu.idevelopment.codenames.repository;

import hu.idevelopment.codenames.repository.entities.DbGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<DbGame, UUID> {
}
