package Lab7.GameElements;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of a timekeeper that runs concurrently with the player threads, as a daemon.
 */
public class TimeKeeper implements Runnable {
    private int seconds;
    private Timer timer;
    private TimerTask endGameTask;
    private TimerTask countdownTask;
    private int countdownSeconds = 10;

    /**
     * Initialize the timekeeper.
     *
     * @param game    the game for which the timekeeper runs.
     * @param seconds the number of seconds allocated for a game.
     */
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
                if (countdownSeconds != 0) {
                    System.out.println(countdownSeconds-- + " second left!");
                } else {
                    System.out.println("Time's up!");
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
