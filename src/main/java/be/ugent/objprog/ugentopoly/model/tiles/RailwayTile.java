package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.layout.Pane;

public class RailwayTile extends Tile {

    private final int cost;

    public RailwayTile(String id, int position, TileType type, int cost) {
        super(id, position, type);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void accept(TileVisitor visitor) {
    }
}
