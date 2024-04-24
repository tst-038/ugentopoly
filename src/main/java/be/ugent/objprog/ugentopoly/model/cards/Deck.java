package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.log.CardDrawnLog;
import be.ugent.objprog.ugentopoly.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final Game game;

    public Deck(Game game) {
        this.game = game;
        this.cards = new ArrayList<>();
    }

    public Game getGame() {
        return game;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard(Player p) {
        if (!cards.isEmpty()) {
            p.getGame().getLogBook().addEntry(new CardDrawnLog(p, cards.getFirst()));
            return cards.removeFirst();
        }
        return null;
    }

    public void addCardToBottom(Card card) {
        cards.add(card);
    }
}