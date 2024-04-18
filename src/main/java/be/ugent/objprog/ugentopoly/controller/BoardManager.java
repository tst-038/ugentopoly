package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.UIUpdater;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class BoardManager {
    private static BoardManager instance;
    private Board board;
    private UIUpdater uiUpdater;
    private TileInfoPaneManager tileInfoPaneManager;
    private Pane currentlySelectedTile;

    private BoardManager(UIUpdater uiUpdater, TileInfoPaneManager tileInfoPaneManager) {
        this.board = GameState.getInstance().getBoard();
        this.uiUpdater = uiUpdater;
        this.tileInfoPaneManager = tileInfoPaneManager;
        this.currentlySelectedTile = null;
    }

    public static BoardManager getInstance(UIUpdater uiUpdater, TileInfoPaneManager tileInfoPaneManager) {
        if (instance == null) {
            instance = new BoardManager(uiUpdater, tileInfoPaneManager);
        }
        return instance;
    }

    public static BoardManager getInstance() {
        return instance;
    }

    public void initializeBoard(AnchorPane rootPane) {
        uiUpdater.colorAreaPanes(board.getAreas());
        uiUpdater.updateTiles(board.getTiles());
        attachTileClickHandlers(rootPane);
    }

    private void attachTileClickHandlers(AnchorPane rootPane) {
        for (Tile tile : board.getTiles()) {
            String tileId = "_" + tile.getPosition();
            Pane tilePane = findTilePane(rootPane, tileId);
            if (tilePane != null) {
                attachTileClickHandler(tilePane);
            }
        }
        ((BorderPane) rootPane.getChildren().getFirst()).getCenter().setOnMouseClicked(event -> hideTileInfoPane());
    }

    private Pane findTilePane(Pane parent, String tileId) {
        if (parent.getId() != null && parent.getId().equals(tileId)) {
            return parent;
        }

        return parent.getChildren().stream()
                .filter(Pane.class::isInstance)
                .map(Pane.class::cast)
                .map(childPane -> findTilePane(childPane, tileId))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public Node findPlayerNode(Player player, AnchorPane rootPane) {
        return rootPane.lookup("#player_" + player.getId());
    }

    private void attachTileClickHandler(Pane tilePane) {
        tilePane.setOnMouseClicked(event -> showTileInfo(tilePane));
    }

    private void showTileInfo(Pane tilePane) {
        String tileId = tilePane.getId();

        if (currentlySelectedTile != null && currentlySelectedTile.getId().equals(tileId)) {
            hideTileInfoPane();
            return;
        }
        Tile tile = board.getTileByPosition(Integer.parseInt(tileId.replace("_", "")));
        if (tile == null) {
            return;
        }

        tileInfoPaneManager.showTileInfo(tile, false);

        if (currentlySelectedTile != null) {
            currentlySelectedTile.getStyleClass().remove("tile-selected");
        }

        if(tileInfoPaneManager.isClosable()){
            tilePane.getStyleClass().add("tile-selected");
            currentlySelectedTile = tilePane;
        }
    }

    private void hideTileInfoPane() {
        tileInfoPaneManager.hideTileInfoPane();
        if (currentlySelectedTile == null) {
            return;
        }
        currentlySelectedTile.getStyleClass().remove("tile-selected");
        currentlySelectedTile = null;
    }
}