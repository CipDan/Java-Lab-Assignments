package Lab7;

import Lab7.GameElements.Game;
import Lab7.PlayerType.Player;

public class Main {
    public static void main(String[] args) {
        Game newGame = new Game(35, 50, 6);
        System.out.println(newGame.getGameBoard().getTokens());
        int idSequence = 0;
        newGame.addPlayer(new Player(++idSequence, "Gigel"));
        newGame.addPlayer(new Player(++idSequence, "Ionel"));
        newGame.addPlayer(new Player(++idSequence, "Marcel"));
        newGame.addPlayer(new Player(++idSequence, "Irinel"));
        newGame.runGame();
        while (newGame.isRunning()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
