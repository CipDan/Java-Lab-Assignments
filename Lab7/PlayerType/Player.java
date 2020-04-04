package Lab7.PlayerType;

import Lab7.GameElements.Board;
import Lab7.GameElements.Game;
import Lab7.GameElements.Token;

import java.util.LinkedList;
import java.util.List;

/**
 * A representation of a player.
 */
public class Player implements Runnable {
    private static final int THINKING_TIME = 10;
    private int id;
    private String name;
    private Game game;
    private int finalScore = 0;
    private List<Token> takenTokens = new LinkedList<>();

    /**
     * Creates a new player.
     *
     * @param id   the player's id.
     * @param name the player's name.
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.game = null;
    }

    /**
     * Assigns the player a game.
     *
     * @param game the game assigned to the player.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Returns the player's id.
     *
     * @return an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the player's name.
     *
     * @return a <code>String</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of the tokens taken by the player.
     *
     * @return a list of tokens.
     */
    public List<Token> getTakenTokens() {
        return takenTokens;
    }

    /**
     * A representation of the player's turn.
     *
     * @return If there are no more tokens or someone managed to reach the goal, it returns false
     * (the player is no longer playing). Otherwise, true (the player is still playing).
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     */
    protected boolean play() throws InterruptedException {
        Board board = game.getGameBoard();
        if (board.getTokens().isEmpty()) {
            game.endGame();
            return false;
        }

        int numOfTokens = takenTokens.size();
        if (numOfTokens < 2) {
            takenTokens.add(board.getAndRemoveToken(0));
            System.out.println("[P" + id + "] " + name + " a extras token-ul (" + takenTokens.
                    get(takenTokens.size() - 1).getNumber() + ")");
        } else {
            boolean foundToken = false;
            int index = 0;
            int difference = Math.abs(takenTokens.get(numOfTokens - 1).getNumber() -
                    takenTokens.get(numOfTokens - 2).getNumber());
            while ((!foundToken) && (index < game.getGameBoard().getSize())) {
                Token token = game.getGameBoard().getTokens().get(index);
                if (Math.abs(takenTokens.get(numOfTokens - 1).getNumber() - token.getNumber()) == difference) {
                    foundToken = true;
                }
                index++;
            }
            if (foundToken) {
                takenTokens.add(board.getAndRemoveToken(index));
                System.out.println("[P" + id + "] " + name + " a extras token-ul (" + takenTokens.
                        get(numOfTokens - 1).getNumber() + ")");
            } else
                System.out.println("[P" + id + "] " + name + " nu a extras nimic.");
        }

        Thread.sleep(THINKING_TIME);
        if (hasLengthK(game.getGoalSize())) {
            game.setWinner(this);
            return false;
        }
        return true;
    }

    /**
     * Checks if the player's arithmetic progression has the desired length.
     *
     * @param k the desired length.
     * @return true if it has, otherwise false.
     */
    public boolean hasLengthK(int k) {
        return takenTokens.size() == k;
    }

    /**
     * Computes the player's score.
     *
     * @return the player's score.
     */
    public int computeFinalScore() {
        if (finalScore == 0) {
            if (game.getHasACompleteProgression() != 0) {
                if (game.getHasACompleteProgression() == id)
                    finalScore = game.getMaxScore();
                else
                    finalScore = 0;
            } else {
                finalScore = takenTokens.size();
            }
        }
        return finalScore;
    }

    /**
     * Writes a stopping message for the player.
     */
    protected void stopMsg() {
        System.out.println(name + " has stopped! ( Score: " + computeFinalScore() + " points ot of " +
                game.getMaxScore() + "! " +
                "is an arithmetic progression of length " + game.getGoalSize() + ": " + hasLengthK(game.getGoalSize()) + " )");
    }

    @Override
    public void run() {
        synchronized (game) {
            try {
                while (game.isRunning()) {
                    while (game.getCurrentPlayer() != this) {
                        game.wait();
                    }
                    if (game.isRunning())
                        play();
                    game.getNextPlayer();
                    game.notifyAll();
                }
                stopMsg();
                System.out.println(id + "." + name + ": " + takenTokens);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "[" + id + "]" + name;
    }
}
