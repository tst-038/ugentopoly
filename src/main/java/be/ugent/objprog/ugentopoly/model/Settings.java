package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;

public class Settings {

    private static final char MONEY_UNIT = PropertyReader.getInstance().get("money_symbol").charAt(0);
    private static final int DOUBLE_ROLLS_TO_JAIL = 3;

    // Singleton instance
    private static Settings instance = null;
    private int startingBalance;
    private int startBonus;

    // Private constructor
    private Settings() {
    }

    // Static method to get the singleton instance
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public static String getMoneyUnit() {
        return String.valueOf(MONEY_UNIT);
    }

    public static int getDoubleRollsToJail() {
        return DOUBLE_ROLLS_TO_JAIL;
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