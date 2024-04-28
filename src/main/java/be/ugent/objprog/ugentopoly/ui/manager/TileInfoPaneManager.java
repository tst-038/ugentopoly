package be.ugent.objprog.ugentopoly.ui.manager;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.logic.listener.TileInfoPaneClosedListener;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileInfoPaneVisitor;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneManager {
    private final AnchorPane tileInfoPane;
    private final TileInfoPaneVisitor tileInfoPaneUpdater;
    private boolean isClosable;
    private TileInfoPaneClosedListener onInfoPaneClosedListener;
    private GameManager gameManager;

    public TileInfoPaneManager(AnchorPane tileInfoPane, GameManager gameManager) {
        this.tileInfoPane = tileInfoPane;
        this.tileInfoPaneUpdater = new TileInfoPaneVisitor(tileInfoPane, gameManager);
        this.isClosable = true;
        this.gameManager = gameManager;
    }

    public void showTileInfo(Tile tile, boolean onVisit) {
        if (tile != null) {
            if (!isClosable){
                return;
            }
            tile.accept(tileInfoPaneUpdater, onVisit);
        }
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
        if (isClosable && tileInfoPane.isVisible()){
            tileInfoPane.setVisible(false);
        }
    }

    public void hideTileInfoPaneAndNotifyListeners() {
        if (isClosable && tileInfoPane.isVisible()) {
            tileInfoPane.setVisible(false);
            if (onInfoPaneClosedListener != null) {
                onInfoPaneClosedListener.onTileInfoPaneClosed();
            }
        }
    }

    public void notifyInfoPaneClosed() {
        if (onInfoPaneClosedListener != null) {
            onInfoPaneClosedListener.onTileInfoPaneClosed();
        }
    }

    public void setPaneClosableAndHide() {
        isClosable = true;
        hideTileInfoPaneAndNotifyListeners();
    }

    public boolean isClosable() {
        return isClosable;
    }
}