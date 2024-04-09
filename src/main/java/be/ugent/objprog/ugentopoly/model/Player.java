package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PlayerMoveLog;
import be.ugent.objprog.ugentopoly.logic.BoardManager;
import be.ugent.objprog.ugentopoly.logic.GameState;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;
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
    private int ownedRailways;

    public Player(String name, Color color) {
        this.id = idCounter++;
        this.name = name.strip();
        this.color = color;
        this.position = 0;
        this.balance = new SimpleIntegerProperty(Settings.getInstance().getStartingBalance());
        this.ownedRailways = 0;
    }

    public int getId(){
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


    //TODO auto update ui position on position change
    public void setPosition(int position) {
        Board board = GameState.getInstance().getBoard();
        Visitable old = board.getTileByPosition(this.position);
        Visitable current = board.getTileByPosition(position);
        GameLogBook.getInstance().addEntry(new PlayerMoveLog(this, old, current));
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