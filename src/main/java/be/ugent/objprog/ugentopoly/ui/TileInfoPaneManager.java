package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.logic.TileInfoPaneClosedListener;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneVisitor;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneManager {
    private final AnchorPane tileInfoPane;
    private final TileInfoPaneVisitor tileInfoPaneUpdater;
    private boolean isClosable;
    private TileInfoPaneClosedListener onInfoPaneClosedListener;

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

    public void setOnInfoPaneClosedListener(TileInfoPaneClosedListener listener) {
        this.onInfoPaneClosedListener = listener;
    }

    public void hideTileInfoPane() {
        if (isClosable && tileInfoPane.isVisible()) {
            tileInfoPane.setVisible(false);
            if (onInfoPaneClosedListener != null) {
                onInfoPaneClosedListener.onTileInfoPaneClosed();
            }
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