package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public interface TileInfoPaneFactory {
    AnchorPane createTileInfoPane(Tile tile, boolean onVisit, Game game);
}
