package Lab7.PlayerType;

import Lab7.GameElements.Board;
import Lab7.GameElements.Token;

public class SmartPlayer extends Player {

    public SmartPlayer(int id, String name) {
        super(id, name);
    }

    @Override
    protected void extractToken(Board board) {
        if (takenTokens.size() < 2) {
            takenTokens.add(board.getAndRemoveToken(0));
            System.out.println("[P" + id + "] " + name + " a extras token-ul (" + takenTokens.
                    get(takenTokens.size() - 1).getNumber() + ")");
        } else {
            boolean foundToken = false;
            int index = 0;
            int difference = Math.abs(takenTokens.get(takenTokens.size() - 1).getNumber() -
                    takenTokens.get(takenTokens.size() - 2).getNumber());
            while ((!foundToken) && (index < game.getGameBoard().getSize())) {
                Token token = game.getGameBoard().getTokens().get(index);
                if (Math.abs(takenTokens.get(takenTokens.size() - 1).getNumber() - token.getNumber()) == difference) {
                    foundToken = true;
                }
                index++;
            }
            if (foundToken) {
                takenTokens.add(board.getAndRemoveToken(index - 1));
                System.out.println("[P" + id + "] " + name + " a extras token-ul (" + takenTokens.
                        get(takenTokens.size() - 1).getNumber() + ")");
            } else
                System.out.println("[P" + id + "] " + name + " nu a extras nimic.");
        }
    }
}
