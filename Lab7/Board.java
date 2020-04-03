package Lab7;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private List<Token> tokens = new LinkedList<>();

    public List<Token> getTokens() {
        return tokens;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public Token findToken(int number) {
        for (Token token : tokens) {
            if (token.getNumber() == number)
                return token;
        }
        return null;
    }

    public synchronized int getSize() {
        return tokens.size();
    }

    public synchronized Token getAndRemoveToken(int index) {
        return tokens.remove(index);
    }
}
