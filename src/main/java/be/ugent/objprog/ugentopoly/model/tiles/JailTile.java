package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class JailTile extends Tile {

    public JailTile(String id, int position) {
        super(id, position, TileType.JAIL);
    }
    @Override
    public void accept(TileVisitor visitor) {

    }
}
