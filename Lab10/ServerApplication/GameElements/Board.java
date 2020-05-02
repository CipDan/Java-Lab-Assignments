package GameElements;

public class Board {
    private int[][] matrix;

    public Board(int size) {
        matrix = new int[size][size];
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                matrix[i][j] = -1;
    }
}
