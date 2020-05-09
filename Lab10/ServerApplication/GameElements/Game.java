package GameElements;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of a game board.
 */
public class Game {
    private Board gameBoard;
    private List<Player> players = new LinkedList<>();
    private List<String> colorOptions = new LinkedList<>(Arrays.asList("black", "white"));

    /**
     * Creates a new game with a brand new board.
     *
     * @param size the size of the board.
     */
    public Game(int size) {
        gameBoard = new Board(size);
    }

    /**
     * Returns the game's players.
     *
     * @return a <code>List</code>.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the game's game board.
     *
     * @return a <code>Board</code>.
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Adds a new player to the game.
     *
     * @param player a new player.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Checks if the given color is not taken.
     *
     * @param color a color.
     * @return true if the color is still available and false otherwise.
     */
    public boolean checkIfColorAvailable(String color) {
        return colorOptions.contains(color);
    }

    /**
     * Removes a color from the option list.
     *
     * @param color the color to be removed.
     */
    public void removeColorOption(String color) {
        colorOptions.remove(color);
    }

    /**
     * Resets the color options.
     */
    public void resetColorOptions() {
        colorOptions.addAll(Arrays.asList("black", "white"));
    }
}
