package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneVisitor;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneManager {
    private static TileInfoPaneManager instance;
    private AnchorPane tileInfoPane;
    private TileInfoPaneVisitor tileInfoPaneUpdater;

    private TileInfoPaneManager(AnchorPane tileInfoPane) {
        this.tileInfoPane = tileInfoPane;
        this.tileInfoPaneUpdater = new TileInfoPaneVisitor(tileInfoPane);
    }

    public static TileInfoPaneManager getInstance(AnchorPane tileInfoPane) {
        if (instance == null) {
            instance = new TileInfoPaneManager(tileInfoPane);
        }
        return instance;
    }

    public static TileInfoPaneManager getInstance() {
        return instance;
    }

    public void showTileInfo(Tile tile) {
        tile.accept(tileInfoPaneUpdater);
        tileInfoPane.setVisible(true);
    }

    public AnchorPane getTileInfoPane() {
        return tileInfoPane;
    }

    public void hideTileInfoPane() {
        tileInfoPane.setVisible(false);
    }
}