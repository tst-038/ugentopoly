package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class TaxTile extends Tile {
    private final int amount;

    public TaxTile(String id, int position, int amount) {
        super(id, position, TileType.TAX);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
