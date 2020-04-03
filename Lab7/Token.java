package Lab7;

public class Token {
    private int number;

    public Token() {

    }

    public Token(int number) {
        this.number = number;
    }

    public static int compareByValue(Token a, Token b) {
        return Integer.compare(a.getNumber(), b.getNumber());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Token(" + number + ')';
    }
}
