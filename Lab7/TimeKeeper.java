package Lab7;

import java.util.Timer;
import java.util.TimerTask;

public class TimeKeeper implements Runnable {
    private Game game;
    private int seconds;
    private Timer timer;
    private TimerTask endGameTask;
    private TimerTask countdownTask;
    private int countdownSeconds = 10;

    TimeKeeper(Game game, int seconds) {
        this.seconds = seconds < countdownSeconds ? seconds + countdownSeconds : seconds;
        timer = new Timer(true);
        endGameTask = new TimerTask() {
            @Override
            public void run() {
                game.endGame();
                System.out.println("Game ended by the timekeeper!");
            }
        };
        countdownTask = new TimerTask() {
            @Override
            public void run() {
                if (countdownSeconds == 1) {
                    System.out.println(countdownSeconds-- + " second left!");
                } else {
                    System.out.println(countdownSeconds-- + " seconds left!");
                }
            }
        };
    }

    @Override
    public void run() {
        timer.schedule(endGameTask, 1000 * seconds);
        timer.scheduleAtFixedRate(countdownTask, (seconds - countdownSeconds) * 1000, 1000);
    }
}
