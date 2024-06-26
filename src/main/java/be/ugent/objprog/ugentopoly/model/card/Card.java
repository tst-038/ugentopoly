package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;

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

    public abstract void execute(Player player, GameManager gameManager);
}