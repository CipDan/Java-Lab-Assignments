package com.example.lab11.game;

import com.example.lab11.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller handling requests regarding the games.
 */
@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    // Get all games
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Create a new game
    @PostMapping("/games")
    public Game createGame(@Valid @RequestBody Game game) {
        return gameRepository.save(game);
    }

    // Get a certain game based on id
    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable(value = "id") Integer gameId) throws GameNotFoundException {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }
}
