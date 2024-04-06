package be.ugent.objprog.ugentopoly.model;

import javafx.scene.paint.Color;

import java.util.Objects;

public class Player {
    private String name;
    private Color color;
    private int position;
    private int balance;

    public Player(String name, Color color) {
        this.name = name.strip();
        this.color = color;
        this.position = Settings.getInstance().getStartAmount();
        this.balance = Settings.getInstance().getStartingBalance(); // Starting balance
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

    public int getBalance() {
        return balance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void updateBalance(int amount) {
        balance += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(color, player.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}