package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.data.reader.CardsReader;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.card.Deck;

public class CardDeckManager {
    private final GameManager gameManager;
    private final Deck chanceDeck;
    private final Deck communityDeck;

    public CardDeckManager(GameManager gameManager) {
        this.gameManager = gameManager;
        chanceDeck = new Deck(gameManager);
        communityDeck = new Deck(gameManager);
    }

    public void init() {
        CardsReader cardsReader = new CardsReader(gameManager);
        cardsReader.readCards();
    }

    public Deck getChanceDeck() {
        return chanceDeck;
    }

    public Deck getCommunityDeck() {
        return communityDeck;
    }
}
