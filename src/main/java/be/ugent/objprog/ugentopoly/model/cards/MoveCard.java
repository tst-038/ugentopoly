package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StartTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

public class MoveCard extends Card {
    private int position;
    private boolean collect;

    public MoveCard(String id, int position, boolean collect, Deck deck) {
        super(id, String.format(PropertyReader.getInstance().get("card.move_card"), position, collect), CardType.MOVE, deck);
        this.position = position;
        this.collect = collect;
    }

    @Override
    public void execute(Player player) {
        player.getPosition().updatePosition(position);
        // if the startposition isnt the default 0, we need to add the offset
        int startPosition = GameState.getInstance().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.START).map(StartTile.class::cast).findFirst().map(Tile::getPosition).orElse(0);
        if (collect && position < player.getPosition().getPos()) {
            Bank.getInstance().deposit(player, Settings.getInstance().getStartBonus());
        }
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}