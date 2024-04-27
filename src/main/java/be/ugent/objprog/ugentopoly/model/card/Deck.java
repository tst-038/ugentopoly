package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.log.event.CardDrawnEvent;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final GameManager gameManager;

    public Deck(GameManager gameManager) {
        this.gameManager = gameManager;
        this.cards = new ArrayList<>();
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard(Player p) {
        if (!cards.isEmpty()) {
            p.getGameManager().getLogBook().addEntry(new CardDrawnEvent(p, cards.getFirst()));
            return cards.removeFirst();
        }
        return null;
    }

    public void addCardToBottom(Card card) {
        cards.add(card);
    }
}