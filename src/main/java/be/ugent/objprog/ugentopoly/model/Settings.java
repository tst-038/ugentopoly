package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;

public class Settings {

    private int startingBalance;
    private int startBonus;
    private final char MONEY_UNIT;
    private final int DOUBLE_ROLLS_TO_JAIL;
    private final int TURNS_IN_JAIL;

    public Settings(PropertyReader propertyReader) {
        MONEY_UNIT = propertyReader.get("money_symbol").charAt(0);
        DOUBLE_ROLLS_TO_JAIL = 3;
        TURNS_IN_JAIL = 3;
    }

    public String getMoneyUnit() {
        return String.valueOf(MONEY_UNIT);
    }

    public int getDoubleRollsToJail() {
        return DOUBLE_ROLLS_TO_JAIL;
    }

    public int getTurnsInJail() {
        return TURNS_IN_JAIL;
    }

    // Method to initialize the settings
    public void initialize(int startingBalance, int startBonus) {
        this.startingBalance = startingBalance;
        this.startBonus = startBonus;
    }

    public int getStartingBalance() {
        return startingBalance;
    }

    public int getStartBonus() {
        return startBonus;
    }

}