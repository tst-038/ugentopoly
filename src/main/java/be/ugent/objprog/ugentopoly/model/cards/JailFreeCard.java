package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;

public class JailFreeCard extends Card {
    public JailFreeCard(String id, Deck deck) {
        super(id, PropertyReader.getInstance().get("card.jail"), CardType.JAIL, deck);
    }

    @Override
    public void execute(Player player) {
        player.resetRemainingTurnsInPrison();
        player.removeCard(this);
        returnToDeck();
    }
}


