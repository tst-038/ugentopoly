package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneVisitor;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneManager {
    private AnchorPane tileInfoPane;
    private TileInfoPaneVisitor tileInfoPaneUpdater;

    public TileInfoPaneManager(AnchorPane tileInfoPane) {
        this.tileInfoPane = tileInfoPane;
        this.tileInfoPaneUpdater = new TileInfoPaneVisitor(tileInfoPane);
    }

    public void showTileInfo(Tile tile) {
        tile.accept(tileInfoPaneUpdater);
        tileInfoPane.setVisible(true);
    }

    public void hideTileInfoPane() {
        tileInfoPane.setVisible(false);
    }
}