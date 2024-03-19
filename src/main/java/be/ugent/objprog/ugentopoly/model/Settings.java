package be.ugent.objprog.ugentopoly.model;

public class Settings {

    private static final char MONEY_UNIT = '€';
    private final int startingBalance;
    private final int startAmount;
    private final int gameBoardSize;

    public Settings(int startingBalance, int startingPosition, int gameBoardSize) {
        this.startingBalance = startingBalance;
        this.startAmount = startingPosition;
        this.gameBoardSize = gameBoardSize;
    }

    public static String getMoneyUnit() {
        return String.valueOf(MONEY_UNIT);
    }

    public int getStartingBalance() {
        return startingBalance;
    }

    public int getStartAmount() {
        return startAmount;
    }

    public int getGameBoardSize() {
        return gameBoardSize;
    }
}
