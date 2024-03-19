package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class StartTile extends Tile {
    public StartTile(String id, int position) {
        super(id, position, TileType.START);
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
