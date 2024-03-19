package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class GoToJailTile extends Tile {
    public GoToJailTile(String id, int position) {
        super(id, position, TileType.GO_TO_JAIL);
    }
    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
