package com.example.lab11.game;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for database management.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
