package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StartTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

public class MoveCard extends Card {
    private final int position;
    private final boolean collect;

    public MoveCard(String id, int position, boolean collect, Deck deck) {
        super(id, String.format(deck.getGame().getPropertyreader().get("card.move_card"), position, collect), CardType.MOVE, deck);
        this.position = position;
        this.collect = collect;
    }

    @Override
    public void execute(Player player, Game game) {
        //TODO fix passed start tile check
        int previousPosition = player.getPosition().getPos();
        player.getPosition().updatePosition(position);
        int startPosition = game.getGameState().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.START).map(StartTile.class::cast).findFirst().map(Tile::getPosition).orElse(0);
        if (collect && position + startPosition < previousPosition) {
            game.getBank().deposit(player, game.getSettings().getStartBonus());
        }
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}