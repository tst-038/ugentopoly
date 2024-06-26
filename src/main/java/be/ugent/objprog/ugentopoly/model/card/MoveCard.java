package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

public class MoveCard extends Card {
    private final int position;
    private final boolean collect;
    private final boolean sendToJail;

    public MoveCard(String id, int position, boolean collect, Deck deck) {
        super(id, String.format(
                deck.getGameManager().getPropertyreader().get(id),
                deck.getGameManager().getBoardManager().getBoard().getTileByPosition(position).getName()) +
                        (collect ?
                        String.format(deck.getGameManager().getPropertyreader().get("label.collect_true"), deck.getGameManager().getSettings().getMoneyUnit()+deck.getGameManager().getSettings().getStartBonus())
                        : deck.getGameManager().getPropertyreader().get("label.collect_false"))
                , CardType.MOVE, deck);
        this.position = position;
        this.collect = collect;
        this.sendToJail = deck.getGameManager().getBoardManager().getBoard().getTileByPosition(position).getType() == TileType.JAIL;
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
        if(sendToJail){
            player.setInJail(true);
        }
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}