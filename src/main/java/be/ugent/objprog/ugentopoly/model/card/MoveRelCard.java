package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class MoveRelCard extends Card {
    private final int relative;

    public MoveRelCard(String id, int relative, Deck deck) {
        super(id, String.format(deck.getGameManager().getPropertyreader().get("card.move_rel_card"), relative), CardType.MOVEREL, deck);
        this.relative = relative;
    }

    @Override
    public void execute(Player player, GameManager gameManager) {
        player.getPosition().updatePosition(player.getPosition().getPos() + relative);
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}
