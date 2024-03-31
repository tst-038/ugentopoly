package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class ChestTile extends Tile {
    public ChestTile(String id, int position) {
        super(id.replaceAll("[0-9]]", ""), position, TileType.CHEST);
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }

}
