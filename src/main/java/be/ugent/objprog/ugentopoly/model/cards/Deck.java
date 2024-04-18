package be.ugent.objprog.ugentopoly.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static Deck chanceDeck;
    private static Deck communityChestDeck;

    private List<Card> cards;

    private Deck() {
        this.cards = new ArrayList<>();
    }

    public static Deck getChanceDeck() {
        if (chanceDeck == null) {
            chanceDeck = new Deck();
        }
        return chanceDeck;
    }

    public static Deck getCommunityChestDeck() {
        if (communityChestDeck == null) {
            communityChestDeck = new Deck();
        }
        return communityChestDeck;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public void addCardToBottom(Card card) {
        cards.add(card);
    }
}