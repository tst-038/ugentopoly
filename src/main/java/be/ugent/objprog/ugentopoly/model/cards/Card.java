package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.model.Player;

public abstract class Card {
    protected String id;
    protected String description;
    protected CardType type;

    Card(String id, String description, CardType type) {
        this.id = id;
        this.description = description;
        this.type = type;
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

    public abstract void execute(Player player);
}