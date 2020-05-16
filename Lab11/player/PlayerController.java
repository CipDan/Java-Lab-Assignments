package com.example.lab11.player;


import com.example.lab11.exception.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller handling requests regarding the players.
 */
@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    // Get all players
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Create a new player
    @PostMapping("/players")
    public Player createPlayer(@Valid @RequestBody Player player) {
        return playerRepository.save(player);
    }

    // Get a certain player based on id
    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable(value = "id") Integer playerId) throws PlayerNotFoundException {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    // Update a player
    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable(value = "id") Integer playerId,
                               @Valid @RequestBody Player playerDetails) throws PlayerNotFoundException {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        player.setName(playerDetails.getName());
        player.setGameId(playerDetails.getGameId());

        Player updatedPlayer = playerRepository.save(player);

        return updatedPlayer;
    }

    // Delete a player
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Integer playerId) throws PlayerNotFoundException {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        playerRepository.delete(player);

        return ResponseEntity.ok().build();
    }
}
