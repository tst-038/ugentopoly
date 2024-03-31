package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class UtilityTile extends Tile {

    private final int cost;

    public UtilityTile(String id, int position, int cost) {
        super(id, position, TileType.UTILITY);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
