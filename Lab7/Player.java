package Lab7;

import java.util.LinkedList;
import java.util.List;

public class Player implements Runnable {
    private static final int THINKING_TIME = 10;
    private int id;
    private String name;
    private Game game;
    private int finalScore = 0;
    private List<Token> takenTokens = new LinkedList<>();

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.game = null;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Token> getTakenTokens() {
        return takenTokens;
    }

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

    public boolean hasLengthK(int k) {
        return takenTokens.size() == k;
    }

    public int computeFinalScore() {
        if (finalScore == 0) {
            if (game.getHasACompleteProgression() != 0){
                if(game.getHasACompleteProgression() == id)
                    finalScore = game.getMaxScore();
                else
                    finalScore = 0;
            }
            else{
                finalScore = takenTokens.size();
            }
        }
        return finalScore;
    }

    protected void stopMsg() {
        System.out.println(name + " s-a oprit! ( Score: " + computeFinalScore() + " points ot of " +
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
                //takenTokens.sort(Token::compareByValue);
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
