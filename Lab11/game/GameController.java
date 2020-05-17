package com.example.lab11.game;

import com.example.lab11.exception.GameNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller handling requests regarding the games.
 */
@RestController
@RequestMapping("/api")
@Api(value = "Games Management System", description = "Operations pertaining to games")
public class GameController {

    @Autowired
    GameRepository gameRepository;

    // Get all games
    @ApiOperation(value = "View a list of all the games in the database", response = List.class)
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Get a certain game based on id
    @ApiOperation(value = "Show a specific game based on its id",
            notes = "Provide an id to look up a specific game from the database", response = Game.class)
    @GetMapping("/games/{id}")
    public Game getGameById(@ApiParam(value = "Id value for the game that is to be retrieved", required = true, example = "1")
                            @PathVariable(value = "id") Integer gameId) throws GameNotFoundException {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }

    // Create a new game
    @ApiOperation(value = "Add a game to the database", response = Game.class)
    @PostMapping("/games")
    public Game createGame(@ApiParam(value = "Game instance to be stored in database", required = true, example = "{\"id\":6, \"idPlayer1\":7, \"idPlayer2\":4, \"name\":\"Anonymus\"")
                           @Valid @RequestBody Game game) {
        return gameRepository.save(game);
    }
}
