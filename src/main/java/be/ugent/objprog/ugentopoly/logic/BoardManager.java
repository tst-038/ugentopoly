package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.util.UIUpdater;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class BoardManager {
    private static BoardManager instance;
    private Board board;
    private AnchorPane rootPane;
    private UIUpdater uiUpdater;
    private TileInfoPaneManager tileInfoPaneManager;
    private Pane currentlySelectedTile;

    private BoardManager(Board board, AnchorPane rootPane, UIUpdater uiUpdater, AnchorPane tileinfoPane) {
        this.board = board;
        this.rootPane = rootPane;
        this.uiUpdater = uiUpdater;
        this.tileInfoPaneManager = TileInfoPaneManager.getInstance(tileinfoPane);
        this.currentlySelectedTile = null;
    }

    public static BoardManager getInstance(Board board, AnchorPane rootPane, UIUpdater uiUpdater, AnchorPane tileinfoPane) {
        if (instance == null) {
            instance = new BoardManager(board, rootPane, uiUpdater, tileinfoPane);
        }
        return instance;
    }

    public static BoardManager getInstance() {
        return instance;
    }

    public void initializeBoard() {
        uiUpdater.colorAreaPanes(board.getAreas());
        uiUpdater.updateTiles(board.getTiles());
        attachTileClickHandlers();
    }

    private void attachTileClickHandlers() {
        for (Tile tile : board.getTiles()) {
            String tileId = "_" + tile.getPosition();
            Pane tilePane = findTilePane(rootPane, tileId);
            if (tilePane != null) {
                attachTileClickHandler(tilePane);
            }
        }
        ((BorderPane) rootPane.getChildren().getFirst()).getCenter().setOnMouseClicked(event -> hideTileInfoPane());
    }


    // TODO simplify
    private Pane findTilePane(Pane parent, String tileId) {
        if (parent.getId() != null && parent.getId().equals(tileId)) {
            return parent;
        }

        for (Node node : parent.getChildren()) {
            if (node instanceof Pane childPane) {
                Pane foundPane = findTilePane(childPane, tileId);
                if (foundPane != null) {
                    return foundPane;
                }
            }
        }

        return null;
    }

    public Pane findPlayerPane(Player player){
        return (Pane) rootPane.lookup("#player_"+player.getName());
    }

    private void attachTileClickHandler(Pane tilePane) {
        tilePane.setOnMouseClicked(event -> showTileInfo(tilePane));
    }

    private void showTileInfo(Pane tilePane) {
        String tileId = tilePane.getId();

        if (currentlySelectedTile != null && currentlySelectedTile.getId().equals(tileId)) {
            hideTileInfoPane();
        } else {
            Tile tile = board.getTileByPosition(Integer.parseInt(tileId.replace("_", "")));
            if (tile != null) {
                tileInfoPaneManager.showTileInfo(tile);

                if (currentlySelectedTile != null) {
                    currentlySelectedTile.getStyleClass().remove("tile-selected");
                }

                tilePane.getStyleClass().add("tile-selected");
                currentlySelectedTile = tilePane;
            }
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