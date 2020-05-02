package GameElements;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board gameBoard;
    private List<Player> players = new LinkedList<>();
    private List<String> colorOptions = new LinkedList<>(Arrays.asList("black", "white"));

    public Game(int size) {
        gameBoard = new Board(size);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public boolean checkIfColorAvailable(String color){
        return colorOptions.contains(color);
    }

    public void removeColorOption(String color){
        colorOptions.remove(color);
    }

    public void resetColorOptions(){
        colorOptions.addAll(Arrays.asList("black", "white"));
    }
}
