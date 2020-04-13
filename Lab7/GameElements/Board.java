package Lab7.GameElements;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the game's board.
 */
public class Board {
    private List<Token> tokens = new LinkedList<>();

    /**
     * Returns the tokens used in the game.
     *
     * @return a list of tokens.
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Adds a new token to the list.
     *
     * @param token the token to be added.
     */
    public void addToken(Token token) {
        tokens.add(token);
    }

    /**
     * Find a token based on it's value.
     *
     * @param number an integer.
     * @return a token if the given number matched a token's value, otherwise null.
     */
    public Token findToken(int number) {
        for (Token token : tokens) {
            if (token.getNumber() == number)
                return token;
        }
        return null;
    }

    public int findAndReturnIndex(int number) {
        int size = tokens.size();
        for (int i = 0; i < size; ++i) {
            if (tokens.get(i).getNumber() == number)
                return i;
        }
        return -1;
    }

    /**
     * Returns the number of tokens used in the game.
     *
     * @return an integer.
     */
    public synchronized int getSize() {
        return tokens.size();
    }

    /**
     * Removes a token from the ones currently available.
     *
     * @param index the position of the token in the list.
     * @return the removed token.
     */
    public synchronized Token getAndRemoveToken(int index) {
        return tokens.remove(index);
    }
}
