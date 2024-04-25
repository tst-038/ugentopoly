package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public interface ITileInfoPaneFactory {
    AnchorPane createTileInfoPane(Tile tile, boolean onVisit);
}
