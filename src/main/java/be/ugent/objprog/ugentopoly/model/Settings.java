package be.ugent.objprog.ugentopoly.model;

public class Settings {

    private static final char MONEY_UNIT = '€';
    private final int startingBalance;
    private final int startAmount;

    public Settings(int startingBalance, int startingPosition) {
        this.startingBalance = startingBalance;
        this.startAmount = startingPosition;
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

}
