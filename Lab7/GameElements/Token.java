package Lab7.GameElements;

/**
 * A representation of the game's tokens/pieces.
 */
public class Token {
    private int number;

    /**
     * Creates a `wildcard` token that can take any value.
     */
    public Token() {

    }

    /**
     * Creates a standard token that has assigned a given value.
     *
     * @param number the value assigned to the token.
     */
    public Token(int number) {
        this.number = number;
    }

    /**
     * A Comparator.
     *
     * @param a first token to be compared.
     * @param b second token to be compared.
     * @return a positive value if a > b, a negative value if a < b and 0 otherwise.
     */
    public static int compareByValue(Token a, Token b) {
        return Integer.compare(a.getNumber(), b.getNumber());
    }

    /**
     * Returns the value of the token.
     *
     * @return an integer.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Assigns a value to the token.
     *
     * @param number the value to be assigned.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Token(" + number + ')';
    }
}
