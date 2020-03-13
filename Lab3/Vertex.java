package Lab3;

/**
 * Model of a two-dimensional vertex. The first coordinate is the index of the item in the list and the second one is the weight of said item.
 */
public class Vertex {
    private int currItem, currWeight;

    public Vertex(int currItem, int currWeight) {
        this.currItem = currItem;
        this.currWeight = currWeight;
    }

    /**
     * Gets first coordinate.
     *
     * @return an <code>int</code> representing the first coordinate
     */
    public int getCurrItem() {
        return currItem;
    }

    /**
     * Gets second coordinate.
     *
     * @return an <code>int</code> representing the second coordinate
     */
    public int getCurrWeight() {
        return currWeight;
    }

    /**
     * Checks if the coordinates given are those of the item.
     *
     * @param x the first coordinate
     * @param y the second coordinate
     * @return <code>true</code> if the coordinates match and <code>false</code> otherwise
     */
    public boolean checkIfEqual(int x, int y) {
        return x == currItem && y == currWeight;
    }

    @Override
    public String toString() {
        return "(" + currItem + ", " + currWeight + ')';
    }
}
