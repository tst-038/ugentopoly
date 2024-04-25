package be.ugent.objprog.ugentopoly.model.player;

import be.ugent.objprog.ugentopoly.model.card.Card;
import be.ugent.objprog.ugentopoly.model.card.CardType;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory {
    private final List<Card> cards;
    private int ownedRailways;
    private int ownedUtilities;

    public PlayerInventory() {
        this.cards = new ArrayList<>();
        this.ownedRailways = 0;
        this.ownedUtilities = 0;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean hasCard(CardType cardType) {
        return cards.stream().anyMatch(card -> card.getType() == cardType);
    }

    public void addOwnedRailway() {
        ownedRailways++;
    }

    public int getOwnedRailways() {
        return ownedRailways;
    }

    public void addOwnedUtility() {
        ownedUtilities++;
    }

    public int getOwnedUtilities() {
        return ownedUtilities;
    }

    public void useGetOutOfJailFreeCard() {
        cards.stream()
                .filter(card -> card.getType() == CardType.JAIL)
                .findFirst().ifPresent(getOutOfJailFreeCard -> cards.remove(getOutOfJailFreeCard));
    }
}
