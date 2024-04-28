package be.ugent.objprog.ugentopoly.ui.manager;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.logic.listener.TileInfoPaneClosedListener;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileInfoPaneVisitor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TileInfoPaneManager {
    private final AnchorPane tileInfoPane;
    private final TileInfoPaneVisitor tileInfoPaneUpdater;
    private boolean isClosable;
    private TileInfoPaneClosedListener onInfoPaneClosedListener;
    private Pane currentlySelectedTile;
    private final GameManager gameManager;

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
            hideTileInfoPane();
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
        if(isClosable){
            hideTileInfoPane();
        }else {
            isClosable = true;
            hideTileInfoPaneAndNotifyListeners();
        }
    }

    public boolean isClosable() {
        return isClosable;
    }

    public void attachTileClickHandler(Pane tilePane) {
        tilePane.setOnMouseClicked(event -> showTileInfo(tilePane));
    }

    private void showTileInfo(Pane tilePane) {
        String tileId = tilePane.getId();

        if (currentlySelectedTile != null && currentlySelectedTile.getId().equals(tileId)) {
            clearTileSelection();
            return;
        }
        Tile tile = gameManager.getGameState().getBoard().getTileByPosition(Integer.parseInt(tileId.replace("_", "")));
        if (tile == null) {
            return;
        }

        showTileInfo(tile, false);

        if (currentlySelectedTile != null) {
            currentlySelectedTile.getStyleClass().remove("tile-selected");
        }

        if (isClosable()) {
            tilePane.getStyleClass().add("tile-selected");
            currentlySelectedTile = tilePane;
        }
    }

    public void clearTileSelection() {
        if (currentlySelectedTile == null) {
            return;
        }
        currentlySelectedTile.getStyleClass().remove("tile-selected");
        currentlySelectedTile = null;
    }
}