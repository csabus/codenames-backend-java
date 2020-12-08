package hu.idevelopment.codenames.repository;

import hu.idevelopment.codenames.repository.entities.DbCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<DbCard, Integer> {
}
