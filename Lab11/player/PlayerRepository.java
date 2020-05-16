package com.example.lab11.player;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for database management.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
