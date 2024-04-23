package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.Player;

import java.util.List;

public abstract class Card {
    protected String id;
    protected String description;
    protected CardType type;
    protected Deck deck;

    Card(String id, String description, CardType type, Deck deck) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.deck = deck;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public CardType getType() {
        return type;
    }

    public void returnToDeck() {
        deck.addCardToBottom(this);
    }

    public abstract void execute(Player player, Game game);
}