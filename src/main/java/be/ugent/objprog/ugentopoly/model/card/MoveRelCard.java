package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class MoveRelCard extends Card {
    private final int relative;

    public MoveRelCard(String id, int relative, Deck deck) {
        super(id, String.format(deck.getGameManager().getPropertyreader().get(id), Math.abs(relative), relative > 0 ? deck.getGameManager().getPropertyreader().get("card.forwards") : deck.getGameManager().getPropertyreader().get("card.backwards")), CardType.MOVEREL, deck);
        this.relative = relative;
    }

    @Override
    public void execute(Player player, GameManager gameManager) {
        gameManager.getPlayerPositionUpdater().update(player, relative);
        gameManager.getTurnManager().previousPlayer();
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}
