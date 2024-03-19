package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class ChanceTile extends Tile {
    public ChanceTile(String id, int position) {
        super(id, position, TileType.CHANCE);
    }
    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
