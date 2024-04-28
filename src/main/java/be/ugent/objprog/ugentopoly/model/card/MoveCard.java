package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class MoveCard extends Card {
    private final int position;
    private final boolean collect;

    public MoveCard(String id, int position, boolean collect, Deck deck) {
        super(id, String.format(
                deck.getGameManager().getPropertyreader().get("card.move_card"),
                deck.getGameManager().getBoardManager().getBoard().getTileByPosition(position).getName(),
                collect ?
                        String.format(deck.getGameManager().getPropertyreader().get("label.collect_true"), deck.getGameManager().getSettings().getMoneyUnit()+deck.getGameManager().getSettings().getStartBonus())
                        : deck.getGameManager().getPropertyreader().get("label.collect_false")
                ), CardType.MOVE, deck);
        this.position = position;
        this.collect = collect;
    }

    @Override
    public void execute(Player player, GameManager gameManager) {
        int oldPos = player.getPosition().getPos();
        int totalTiles = gameManager.getBoardManager().getBoard().getTiles().size();
        int relativeMovement;

        if (collect) {
            relativeMovement = (position - oldPos + totalTiles) % totalTiles;
        } else {
            relativeMovement = (oldPos - position + totalTiles) % totalTiles;
            relativeMovement = -relativeMovement;
        }

        gameManager.getPlayerPositionUpdater().update(player, relativeMovement);
        gameManager.getTurnManager().previousPlayer();
        player.getInventory().removeCard(this);
    }
}