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
        super(id, String.format(PropertyReader.getInstance().get("card.move_card"), position, collect), CardType.MOVE, deck);
        this.position = position;
        this.collect = collect;
    }

    @Override
    public void execute(Player player, Game game) {
        player.getPosition().updatePosition(position);
        int startPosition = game.getGameState().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.START).map(StartTile.class::cast).findFirst().map(Tile::getPosition).orElse(0);
        if (collect && position < player.getPosition().getPos()) {
            game.getBank().deposit(player, Settings.getInstance().getStartBonus());
        }
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}