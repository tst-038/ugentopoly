package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneVisitor;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameController {
    private Board board;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane tileInfoPane;

    private TileInfoPaneVisitor tileInfoPaneUpdater;

    private Pane currentlySelectedTile = null;


    public void initializeBoard(Board board) {
        this.board = board;
        tileInfoPaneUpdater = new TileInfoPaneVisitor(tileInfoPane, board.getSettings());

        UIUpdater uiUpdater = new UIUpdater(rootPane);
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
        // Attach click handler to the root pane to close the info pane when clicking outside of a tile
        ((BorderPane) rootPane.getChildren().getFirst()).getCenter().setOnMouseClicked(event -> hideTileInfoPane());
    }

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

    private void attachTileClickHandler(Pane tilePane) {
        tilePane.setOnMouseClicked(event -> showTileInfo(tilePane));
    }

    private void showTileInfo(Pane tilePane) {
        String tileId = tilePane.getId();

        if (currentlySelectedTile != null && currentlySelectedTile.getId().equals(tileId)) {
            // If the clicked tile is already selected, close the info pane
            hideTileInfoPane();
        } else {
            // If a different tile is clicked, update the info pane and show it
            Tile tile = board.getTileByPosition(Integer.parseInt(tileId.replace("_", "")));
            if (tile != null) {
                tile.accept(tileInfoPaneUpdater);
                tileInfoPane.setVisible(true);

                if (currentlySelectedTile != null) {
                    currentlySelectedTile.getStyleClass().remove("tile-selected");
                }

                tilePane.getStyleClass().add("tile-selected");
                currentlySelectedTile = tilePane;
            }
        }
    }

    private void hideTileInfoPane() {
        tileInfoPane.setVisible(false);
        currentlySelectedTile.getStyleClass().remove("tile-selected");
        currentlySelectedTile = null;
    }
}