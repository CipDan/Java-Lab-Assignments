package Lab7.PlayerType;

import Lab7.GameElements.Board;

import java.util.Scanner;

public class ManualPlayer extends Player {
    private Scanner scanner;

    public ManualPlayer(int id, String name) {
        super(id, name);
        scanner = new Scanner(System.in);
    }

    @Override
    protected void extractToken(Board board) {
        int extracted;
        do {
            System.out.println("[P" + getId() + "] " + getName() + " - enter token value: ");
            int tokenVal = scanner.nextInt();
            extracted = board.findAndReturnIndex(tokenVal);
            if (extracted == -1)
                System.out.println("[P" + getId() + "] " + getName() + ": Please choose an available token!");
            else {
                takenTokens.add(board.getAndRemoveToken(extracted));
                System.out.println("[P" + id + "] " + name + " a extras token-ul (" + takenTokens.
                        get(takenTokens.size() - 1).getNumber() + ")");
            }
        } while (extracted == -1);
    }
}
