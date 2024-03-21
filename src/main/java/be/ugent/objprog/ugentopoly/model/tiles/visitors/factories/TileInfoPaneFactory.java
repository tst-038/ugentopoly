package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public interface TileInfoPaneFactory {
    AnchorPane createTileInfoPane(Tile tile);
}
