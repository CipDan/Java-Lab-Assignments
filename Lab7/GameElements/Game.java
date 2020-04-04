package Lab7.GameElements;

import Lab7.PlayerType.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A representation of the arithmetic progression game.
 */
public class Game {
    private Board gameBoard;
    private List<Player> players = new LinkedList<>();
    private final List<Thread> threads = new ArrayList<>();
    private boolean running = false;
    private int goalSize;
    private int maxScore;
    private int hasACompleteProgression = 0;
    private int currentlyPlayingIndex;
    private Player winnerPlayer;
    private TimeKeeper timeKeeper;
    private static final int MAXIMUM_NUMBER_OF_SECONDS = 10;

    /**
     * Initialize the game's elements (board, tokens).
     *
     * @param n the number of tokens used.
     * @param m the tokens can be assigned values from 1 to m.
     * @param k the length of the arithmetic progression.
     */
    public Game(int n, int m, int k) {
        if (n > m) {
            System.out.println("The maximum value should be bigger than the number of tokens!");
        } else {
            goalSize = k;
            maxScore = n;
            gameBoard = new Board();
            Random rand = new Random();
            int aux;
            for (int i = 0; i < n; ++i) {
                aux = rand.ints(1, m + 1).limit(1).findFirst().getAsInt();
                while (gameBoard.findToken(aux) != null) {
                    aux = rand.ints(1, m + 1).limit(1).findFirst().getAsInt();
                }
                gameBoard.addToken(new Token(aux));
            }
            gameBoard.getTokens().sort(Token::compareByValue);
        }
    }

    /**
     * Returns the `running` property of the game.
     *
     * @return a boolean value.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Adds players to the game.
     *
     * @param player the player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    /**
     * Returns the game's board.
     *
     * @return a <code>Board</code>.
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Runs the game.
     */
    public synchronized void runGame() {
        running = true;
        winnerPlayer = null;
        for (Player player : players) {
            Thread thread = new Thread(player);
            threads.add(thread);
            thread.start();
        }
        currentlyPlayingIndex = 0;

        timeKeeper = new TimeKeeper(this, MAXIMUM_NUMBER_OF_SECONDS);
        Thread timeThread = new Thread(timeKeeper);
        timeThread.setDaemon(true);
        timeThread.start();
    }

    /**
     * Returns the length that the arithmetic progression should have.
     *
     * @return an integer.
     */
    public int getGoalSize() {
        return goalSize;
    }

    /**
     * Gets the maximum score that a player can have.
     *
     * @return an integer.
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Gets the id of the player that managed to build an arithmetic progression with the desired size, or 0 if there is none.
     *
     * @return an integer.
     */
    public int getHasACompleteProgression() {
        return hasACompleteProgression;
    }

    /**
     * Sets the winner.
     *
     * @param player the player that won the game.
     */
    public synchronized void setWinner(Player player) {
        if (running) {
            running = false;
            winnerPlayer = player;
            hasACompleteProgression = player.getId();
            System.out.println("[P" + winnerPlayer.getId() + "] " + winnerPlayer.getName() +
                    " is the winner!");
            System.out.println("\uD83D\uDC80 GAME OVER \uD83D\uDC80");
        }
    }

    /**
     * Finishes the game if no player has reached the goal.
     */
    public synchronized void endGame() {
        if (running) {
            int bestScore = 0;
            Player tempWinnerPlayer = null;
            for (Player player : players) {
                int tempScore = player.computeFinalScore();
                if (tempScore > bestScore) {
                    bestScore = tempScore;
                    tempWinnerPlayer = player;
                }
            }
            setWinner(tempWinnerPlayer);
        }
    }

    /**
     * Gets the player that should go next.
     *
     * @return a <code>Player</code>.
     */
    public Player getNextPlayer() {
        currentlyPlayingIndex = (++currentlyPlayingIndex) % players.size();
        return players.get(currentlyPlayingIndex);
    }

    /**
     * Gets the player whose turn is taking place.
     *
     * @return a <code>Player</code>.
     */
    public Player getCurrentPlayer() {
        return players.get(currentlyPlayingIndex);
    }
}
