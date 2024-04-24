package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.cards.Deck;

public class DeckManager {
    private final Deck chanceDeck;
    private final Deck communityDeck;

    public DeckManager(Game game) {
        chanceDeck = new Deck(game);
        communityDeck = new Deck(game);
    }

    public Deck getChanceDeck() {
        return chanceDeck;
    }

    public Deck getCommunityDeck() {
        return communityDeck;
    }
}
