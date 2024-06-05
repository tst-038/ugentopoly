package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class JailFreeCard extends Card {
    public JailFreeCard(String id, Deck deck) {
        super(id, deck.getGameManager().getPropertyreader().get(id), CardType.JAIL, deck);
    }

    @Override
    public void execute(Player player, GameManager gameManager) {
        if (player.getRemainingTurnsInPrison() > 0) {
            player.setInJail(false);
            player.getInventory().removeCard(this);
            returnToDeck();
        }
    }
}


