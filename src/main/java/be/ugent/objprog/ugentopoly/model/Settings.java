package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.data.PropertyReader;

public class Settings {

    private static final char MONEY_UNIT = PropertyReader.getInstance().get("money_symbol").charAt(0);
    // Singleton instance
    private static Settings instance = null;
    private int startingBalance;
    private int startAmount;

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

    // Method to initialize the settings
    public void initialize(int startingBalance, int startingPosition) {
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