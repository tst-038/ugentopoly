package be.ugent.objprog.ugentopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;
    private int position;
    private IntegerProperty balance;
    private int ownedRailways;

    public Player(String name, Color color) {
        this.name = name.strip();
        this.color = color;
        this.position = Settings.getInstance().getStartBonus();
        this.balance = new SimpleIntegerProperty(Settings.getInstance().getStartingBalance());
        this.ownedRailways = 0;
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public IntegerProperty balanceProperty() {
        return balance;
    }

    public int getBalance() {
        return balance.get();
    }

    public void setBalance(int balance) {
        this.balance.set(balance);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addRailway() {
        ownedRailways++;
    }

    public void removeRailway() {
        if (ownedRailways > 0) {
            ownedRailways--;
        }
    }
}