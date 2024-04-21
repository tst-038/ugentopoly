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
    private int position = 0;
    private final IntegerProperty balance;
    private final IntegerProperty networth;
    private int ownedRailways = 0;
    private int ownedUtility = 0;
    private PlayerPion pion;
    private List<Card> cards;
    private int remainingTurnsInPrison = 0;
    private int doubleRolls = 0;

    public Player(String name, Color color) {
        this.id = idCounter++;
        this.name = name.strip();
        this.color = color;
        this.balance = new SimpleIntegerProperty(Settings.getInstance().getStartingBalance());
        this.networth = new SimpleIntegerProperty(0);
        this.cards = new ArrayList<>();
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
        int m = GameState.getInstance().getBoard().getTiles().size();
        // True modulo operator from python in java...
        position = ((position % m) + m) % m;
        Board board = GameState.getInstance().getBoard();
        Visitable current = board.getTileByPosition(position);
        GameLogBook.getInstance().addEntry(new PlayerMoveLog(this, current));
        this.position = position;
        pion.updatePosition(position);
        // Trigger the onLand event for the landed tile
        Tile landedTile = GameState.getInstance().getBoard().getTileByPosition(position);
        landedTile.onVisit(this);
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

    public void addOwnedRailway() {
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

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards(){
        return cards;
    }

    public boolean hasCard(String cardId) {
        return cards.stream().anyMatch(card -> card.getId().equals(cardId));
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

    public void useGetOutOfJailFreeCard() {
        System.out.println("usegetout of");;
        Card getOutOfJailFreeCard = cards.stream()
                .filter(card -> card.getType() == CardType.JAIL)
                .findFirst()
                .orElse(null);

        if (getOutOfJailFreeCard != null) {
            cards.remove(getOutOfJailFreeCard);
            resetRemainingTurnsInPrison();
        }
    }
}