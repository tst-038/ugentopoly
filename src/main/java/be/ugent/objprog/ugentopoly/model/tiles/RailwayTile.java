package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class RailwayTile extends Tile {

    private final int price;

    public RailwayTile(String id, int position, int cost) {
        super(id, position, TileType.RAILWAY);
        this.price = cost;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
