package com.example.lab11.player;


import com.example.lab11.exception.PlayerNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller handling requests regarding the players.
 */
@RestController
@RequestMapping("/api")
@Api(value = "Players Management System", description = "Operations pertaining to players")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    // Get all players
    @ApiOperation(value = "View a list of all the players in the database", response = List.class)
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Get a certain player based on id
    @ApiOperation(value = "Show a specific player based on its id",
            notes = "Provide an id to look up a specific player from the database", response = Player.class)
    @GetMapping("/players/{id}")
    public Player getPlayerById(@ApiParam(value = "Id value for the player that is to be retrieved", required = true, example = "1")
                                @PathVariable(value = "id") Integer playerId) throws PlayerNotFoundException {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    // Create a new player
    @ApiOperation(value = "Add a player to the database", response = Player.class)
    @PostMapping("/players")
    public Player createPlayer(@ApiParam(value = "Player instance to be stored in database", required = true, example = "{\"id\": 2, \"name\": \"anon\", \"gameId\": 10")
                               @Valid @RequestBody Player player) {
        return playerRepository.save(player);
    }

    // Update a player
    @ApiOperation(value = "Update a player", response = Player.class)
    @PutMapping("/players/{id}")
    public Player updatePlayer(@ApiParam(value = "Player id to update a specific player in the database", required = true, example = "1")
                               @PathVariable(value = "id") Integer playerId,
                               @ApiParam(value = "Data to update", required = true, example = "{\"name\": \"anon\", \"gameId\": 10}")
                               @Valid @RequestBody Player playerDetails) throws PlayerNotFoundException {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        player.setName(playerDetails.getName());
        player.setGameId(playerDetails.getGameId());

        Player updatedPlayer = playerRepository.save(player);

        return updatedPlayer;
    }

    // Delete a player
    @ApiOperation(value = "Delete a player", response = ResponseEntity.class)
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@ApiParam(value = "Player id to delete a specific player from the database", required = true, example = "1")
                                          @PathVariable(value = "id") Integer playerId) throws PlayerNotFoundException {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        playerRepository.delete(player);

        return ResponseEntity.ok().build();
    }
}
