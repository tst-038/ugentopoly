package be.ugent.objprog.ugentopoly.model;

public class Settings {

    private final int startingBalance;
    private final int startingPosition;
    private final int gameBoardSize;

    public Settings(int startingBalance, int startingPosition, int gameBoardSize) {
        this.startingBalance = startingBalance;
        this.startingPosition = startingPosition;
        this.gameBoardSize = gameBoardSize;
    }

    public int getStartingBalance() {
        return startingBalance;
    }

    public int getStartingPosition() {
        return startingPosition;
    }

    public int getGameBoardSize() {
        return gameBoardSize;
    }
}
