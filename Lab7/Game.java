package Lab7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    public boolean isRunning() {
        return running;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public synchronized void runGame() {
        running = true;
        winnerPlayer = null;
        for (Player player : players) {
            Thread thread = new Thread(player);
            threads.add(thread);
            thread.start();
        }
        currentlyPlayingIndex = 0;

        timeKeeper = new TimeKeeper(this,MAXIMUM_NUMBER_OF_SECONDS);
        Thread timeThread = new Thread(timeKeeper);
        timeThread.setDaemon(true);
        timeThread.start();
    }

    public int getGoalSize() {
        return goalSize;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int getHasACompleteProgression() {
        return hasACompleteProgression;
    }

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

    public synchronized void endGame() {
        if (running) {
            int bestScore = 0;
            Player tempWinnerPlayer = null;
            for (Player player : players) {
                int tempScore = player.computeFinalScore();
                if(tempScore > bestScore){
                    bestScore = tempScore;
                    tempWinnerPlayer = player;
                }
            }
            setWinner(tempWinnerPlayer);
            //System.out.println("\uD83D\uDC80 GAME OVER \uD83D\uDC80");
        }
    }

    public Player getNextPlayer() {
        currentlyPlayingIndex = (++currentlyPlayingIndex) % players.size();
        return players.get(currentlyPlayingIndex);
    }

    public Player getCurrentPlayer() {
        return players.get(currentlyPlayingIndex);
    }
}
