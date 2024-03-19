package be.ugent.objprog.ugentopoly.model;

public class Player {
    private final String name;
    private int position;
    private int balance;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.balance = 1500; // Starting balance
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

    public void updateBalance(int amount) {
        balance += amount;
    }
}