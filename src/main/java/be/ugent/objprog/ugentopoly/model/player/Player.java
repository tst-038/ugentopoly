package be.ugent.objprog.ugentopoly.model.player;

import be.ugent.objprog.ugentopoly.log.event.GoToJailEvent;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.ui.element.PlayerPawn;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

public class Player {
    private final IntegerProperty balance;
    private final IntegerProperty networth;
    private final int id;
    private String name;
    private Color color;
    private PlayerPawn pawn;
    private final PlayerInventory inventory;
    private final PlayerPosition position;
    private GameManager gameManager;
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

    public void setGame(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public int getRemainingTurnsInPrison() {
        return remainingTurnsInPrison;
    }

    public void setRemainingTurnsInPrison(int remainingTurnsInPrison) {
        this.remainingTurnsInPrison = remainingTurnsInPrison;
        gameManager.getLogBook().addEntry(new GoToJailEvent(this));
    }

    public void decreaseRemainingTurnsInPrison() {
        this.remainingTurnsInPrison--;
    }

    public void setInJail(boolean inJail) {
        if(inJail) {
            setRemainingTurnsInPrison(gameManager.getSettings().getTurnsInJail());
        } else {
            this.remainingTurnsInPrison = 0;
        }
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

    public PlayerPawn getPawn() {
        return pawn;
    }

    public void setPawn(PlayerPawn pawn) {
        getPosition().removeListener(this.pawn);
        this.pawn = pawn;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public PlayerPosition getPosition() {
        return position;
    }
}