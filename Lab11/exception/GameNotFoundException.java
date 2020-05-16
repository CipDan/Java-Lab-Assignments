package com.example.lab11.exception;

/**
 * A custom exception for when no game with a given id was found in the database.
 */
public class GameNotFoundException extends Exception {

    public GameNotFoundException(long gameId) {
        super(String.format("Game is not found with id : '%s'", gameId));
    }
}
