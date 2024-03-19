package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class FreeParkingTile extends Tile {
    public FreeParkingTile(String id, int position) {
        super(id, position, TileType.FREE_PARKING);
    }
    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
