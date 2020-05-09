package GameElements;

/**
 * An implementation of a game board.
 */
public class Board {
    private int[][] matrix;

    /**
     * Creates and initializes a board.
     *
     * @param size the board's size.
     */
    public Board(int size) {
        matrix = new int[size][size];
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                matrix[i][j] = -1;
    }
}
