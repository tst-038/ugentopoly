package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.Player;

public class MoveRelCard extends Card {
    private final int relative;

    public MoveRelCard(String id, int relative, Deck deck) {
        super(id, String.format(deck.getGame().getPropertyreader().get("card.move_rel_card"), relative), CardType.MOVEREL, deck);
        this.relative = relative;
    }

    @Override
    public void execute(Player player, Game game) {
        player.getPosition().updatePosition(player.getPosition().getPos() + relative);
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}
