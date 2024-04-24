package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.log.GoToJailLog;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

public class Player {
    private final IntegerProperty balance;
    private final IntegerProperty networth;
    private final int id;
    private String name;
    private Color color;
    private PlayerPion pion;
    private final PlayerInventory inventory;
    private final PlayerPosition position;
    private Game game;
    private int doubleRolls = 0;
    private int remainingTurnsInPrison = 0;

    public Player(int id, String name, Color color, int startBalance) {
        this.id = id;
        this.name = name.strip();
        this.color = color;
        this.balance = new SimpleIntegerProperty(startBalance);
        this.networth = new SimpleIntegerProperty(0);
        this.inventory = new PlayerInventory();
        this.position = new PlayerPosition(this);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public int getRemainingTurnsInPrison() {
        return remainingTurnsInPrison;
    }

    public void setRemainingTurnsInPrison(int remainingTurnsInPrison) {
        this.remainingTurnsInPrison = remainingTurnsInPrison;
        game.getLogBook().addEntry(new GoToJailLog(this));
    }

    public void decreaseRemainingTurnsInPrison() {
        this.remainingTurnsInPrison--;
    }

    public void resetRemainingTurnsInPrison() {
        this.remainingTurnsInPrison = 0;
    }

    public void addDoubleRoll() {
        doubleRolls++;
    }

    public void resetDoubleRolls() {
        doubleRolls = 0;
    }

    public int getDoubleRolls() {
        return doubleRolls;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public IntegerProperty balanceProperty() {
        return balance;
    }

    public IntegerProperty networthProperty() {
        return networth;
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

    public PlayerPion getPion() {
        return pion;
    }

    public void setPion(PlayerPion pion) {
        getPosition().removeListener(this.pion);
        this.pion = pion;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public PlayerPosition getPosition() {
        return position;
    }
}