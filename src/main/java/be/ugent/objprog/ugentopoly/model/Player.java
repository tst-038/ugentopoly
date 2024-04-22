package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.GoToJailLog;
import be.ugent.objprog.ugentopoly.log.PlayerMoveLog;
import be.ugent.objprog.ugentopoly.model.cards.Card;
import be.ugent.objprog.ugentopoly.model.cards.CardType;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static int idCounter = 0;
    private int id;
    private String name;
    private Color color;
    private final IntegerProperty balance;
    private final IntegerProperty networth;
    private PlayerPion pion;
    private PlayerInventory inventory;
    private PlayerPosition position;
    private int doubleRolls = 0;
    private int remainingTurnsInPrison = 0;

    public Player(String name, Color color) {
        this.id = idCounter++;
        this.name = name.strip();
        this.color = color;
        this.balance = new SimpleIntegerProperty(Settings.getInstance().getStartingBalance());
        this.networth = new SimpleIntegerProperty(0);
        this.inventory = new PlayerInventory();
        this.position = new PlayerPosition(this);
    }

    public int getRemainingTurnsInPrison() {
        return remainingTurnsInPrison;
    }

    public void decreaseRemainingTurnsInPrison() {
        this.remainingTurnsInPrison--;
    }

    public void resetRemainingTurnsInPrison() {
        this.remainingTurnsInPrison = 0;
    }

    public void setRemainingTurnsInPrison(int remainingTurnsInPrison) {
        this.remainingTurnsInPrison = remainingTurnsInPrison;
        GameLogBook.getInstance().addEntry(new GoToJailLog(this.getName()));
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

    public void setName(String name) {
        this.name = name.strip();
    }

    public String getName() {
        return name;
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

    public void setPion(PlayerPion pion) {
        this.pion = pion;
    }

    public PlayerPion getPion() {
        return pion;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public PlayerPosition getPosition() {
        return position;
    }
}