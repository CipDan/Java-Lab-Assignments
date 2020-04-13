package Lab7;

import Lab7.GameElements.Game;
import Lab7.PlayerType.ManualPlayer;
import Lab7.PlayerType.RandomPlayer;
import Lab7.PlayerType.SmartPlayer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game newGame = new Game(35, 50, 6);
        System.out.println(newGame.getGameBoard().getTokens());
        int idSequence = 0;
        Scanner scanner = new Scanner(System.in);
        newGame.addPlayer(new RandomPlayer(++idSequence, "Gigel"));
        newGame.addPlayer(new SmartPlayer(++idSequence, "Ionel"));
        newGame.addPlayer(new ManualPlayer(++idSequence, "Marcel"));
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
