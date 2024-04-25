package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.StartTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

public class MoveCard extends Card {
    private final int position;
    private final boolean collect;

    public MoveCard(String id, int position, boolean collect, Deck deck) {
        super(id, String.format(deck.getGameManager().getPropertyreader().get("card.move_card"), position, collect), CardType.MOVE, deck);
        this.position = position;
        this.collect = collect;
    }

    @Override
    public void execute(Player player, GameManager gameManager) {
        int oldPos = player.getPosition().getPos();
        player.getPosition().updatePosition(position);
        int startPosition = gameManager.getGameState().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.START).map(StartTile.class::cast).findFirst().map(Tile::getPosition).orElse(0);
        if(oldPos < startPosition &&  startPosition < position && collect){
            gameManager.getBank().deposit(player, gameManager.getSettings().getStartBonus());
        }
        player.getInventory().removeCard(this);
    }
}