package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PlayerMoveLog;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

public class Player {
    private static int idCounter = 0;
    private int id;
    private String name;
    private Color color;
    private int position;
    private final IntegerProperty balance;
    private final IntegerProperty networth;
    private int ownedRailways;
    private int ownedUtility;
    private PlayerPion pion;

    public Player(String name, Color color) {
        this.id = idCounter++;
        this.name = name.strip();
        this.color = color;
        this.position = 0;
        this.balance = new SimpleIntegerProperty(Settings.getInstance().getStartingBalance());
        this.networth = new SimpleIntegerProperty(0);
        this.ownedRailways = 0;
        this.ownedUtility = 0;
    }

    public void setNetworth(int networth) {
        this.networth.set(networth);
    }

    public int getNetworth() {
        return networth.get();
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        Board board = GameState.getInstance().getBoard();
        Visitable old = board.getTileByPosition(this.position);
        Visitable current = board.getTileByPosition(position);
        GameLogBook.getInstance().addEntry(new PlayerMoveLog(this, old, current));
        this.position = position;
        pion.updatePosition(position);
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
        this.pion = pion;
    }

    public void addRailway() {
        ownedRailways++;
    }

    public int getOwnedRailways() {
        return ownedRailways;
    }

    public void addOwnedUtility() {
        ownedUtility++;
    }

    public int getOwnedUtility() {
        return ownedUtility;
    }
}