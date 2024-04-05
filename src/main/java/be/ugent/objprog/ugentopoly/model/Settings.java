package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.data.PropertyReader;

public class Settings {

    private static final char MONEY_UNIT = PropertyReader.getInstance().get("money_symbol").charAt(0);
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
