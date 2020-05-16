package com.example.lab11.exception;

/**
 * A custom exception for when no player with a given id was found in the database.
 */
public class PlayerNotFoundException extends Exception {

    public PlayerNotFoundException(long playerId) {
        super(String.format("Player is not found with id : '%s'", playerId));
    }
}
