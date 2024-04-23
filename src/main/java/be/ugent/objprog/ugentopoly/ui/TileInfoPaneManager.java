package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneVisitor;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneManager {
    private final AnchorPane tileInfoPane;
    private final TileInfoPaneVisitor tileInfoPaneUpdater;
    private boolean isClosable;

    public TileInfoPaneManager(AnchorPane tileInfoPane, Game game) {
        this.tileInfoPane = tileInfoPane;
        this.tileInfoPaneUpdater = new TileInfoPaneVisitor(tileInfoPane, game);
        this.isClosable = true;
    }

    public void showTileInfo(Tile tile, boolean onVisit) {
        if (tile != null && !isClosable) {
            return;
        }
        tile.accept(tileInfoPaneUpdater, onVisit);
        isClosable = !onVisit;
        tileInfoPane.setVisible(true);
    }

    public AnchorPane getTileInfoPane() {
        return tileInfoPane;
    }

    public void hideTileInfoPane() {
        if (isClosable) {
            tileInfoPane.setVisible(false);
        }
    }

    public void setPaneClosableAndHide() {
        isClosable = true;
        hideTileInfoPane();
    }

    public boolean isClosable() {
        return isClosable;
    }
}