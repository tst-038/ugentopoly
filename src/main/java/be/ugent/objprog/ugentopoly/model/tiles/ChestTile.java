package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class ChestTile extends Tile {
    public ChestTile(String id, int position) {
        super(id, position, TileType.CHEST);
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }

}
