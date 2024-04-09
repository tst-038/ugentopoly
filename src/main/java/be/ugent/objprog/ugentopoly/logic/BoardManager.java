package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Board;
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
    private AnchorPane rootPane;
    private UIUpdater uiUpdater;
    private TileInfoPaneManager tileInfoPaneManager;
    private Pane currentlySelectedTile;

    private BoardManager(AnchorPane rootPane, UIUpdater uiUpdater, AnchorPane tileinfoPane) {
        this.board = GameState.getInstance().getBoard();
        this.rootPane = rootPane;
        this.uiUpdater = uiUpdater;
        this.tileInfoPaneManager = TileInfoPaneManager.getInstance(tileinfoPane);
        this.currentlySelectedTile = null;
    }

    public static BoardManager getInstance(AnchorPane rootPane, UIUpdater uiUpdater, AnchorPane tileinfoPane) {
        if (instance == null) {
            instance = new BoardManager(rootPane, uiUpdater, tileinfoPane);
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

    public Node findPlayerNode(Player player){
        return  rootPane.lookup("#player_"+player.getId());
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

        tileInfoPaneManager.showTileInfo(tile);

        if (currentlySelectedTile != null) {
            currentlySelectedTile.getStyleClass().remove("tile-selected");
        }

        tilePane.getStyleClass().add("tile-selected");
        currentlySelectedTile = tilePane;
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