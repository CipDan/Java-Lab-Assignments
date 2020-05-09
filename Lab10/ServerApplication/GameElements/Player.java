package GameElements;

/**
 * An implementation of a player.
 */
public class Player {
    private String name;
    private String color = null;

    /**
     * Creates a player with the given name.
     *
     * @param name a name for the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Assigns a pion color to the player.
     *
     * @param color the assigned color.
     */
    public void assignColor(String color) {
        this.color = color;
    }

    /**
     * Checks if a color has been assigned to the player.
     *
     * @return true if there has and false otherwise.
     */
    public boolean checkIfColorWasAssigned() {
        return color == null;
    }

    /**
     * Returns the player's name.
     *
     * @return a <code>String</code>.
     */
    public String getName() {
        return name;
    }
}
