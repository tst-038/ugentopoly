package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.log.CardDrawnLog;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static Deck chanceDeck;
    private static Deck communityChestDeck;

    private final List<Card> cards;

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

    public Card drawCard(Player p) {
        if (!cards.isEmpty()) {
            GameLogBook.getInstance().addEntry(new CardDrawnLog(p.getName(), cards.getFirst()));
            return cards.removeFirst();
        }
        return null;
    }

    public void addCardToBottom(Card card) {
        cards.add(card);
    }
}