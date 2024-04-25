package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.card.Deck;

public class CardDeckManager {
    private final Deck chanceDeck;
    private final Deck communityDeck;

    public CardDeckManager(GameManager gameManager) {
        chanceDeck = new Deck(gameManager);
        communityDeck = new Deck(gameManager);
    }

    public Deck getChanceDeck() {
        return chanceDeck;
    }

    public Deck getCommunityDeck() {
        return communityDeck;
    }
}
