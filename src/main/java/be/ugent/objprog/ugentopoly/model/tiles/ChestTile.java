package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.layout.Pane;

public class ChestTile extends Tile {
    protected ChestTile(String id, int position, TileType type) {
        super(id, position, type);
    }

    @Override
    public void accept(TileVisitor visitor) {
    }
}
