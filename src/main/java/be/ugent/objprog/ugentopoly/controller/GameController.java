package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneUpdater;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class GameController {
    private Board board;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane tileInfoPane;

    private TileInfoPaneUpdater tileInfoPaneUpdater;

    public void initializeBoard(Board board) {
        this.board = board;
        tileInfoPaneUpdater = new TileInfoPaneUpdater(tileInfoPane, board.getSettings());

        UIUpdater uiUpdater = new UIUpdater(rootPane);
        uiUpdater.colorAreaPanes(board.getAreas());
        uiUpdater.updateTiles(board.getTiles());

        attachTileClickHandlers();
    }

    private void attachTileClickHandlers() {
        BorderPane boardPane = (BorderPane) rootPane.getChildren().get(0);
        boardPane.getChildren().forEach(node -> attachClickHandlers((Pane) node));
    }

    private void attachClickHandlers(Pane parent) {
        if (parent instanceof HBox || parent instanceof VBox || parent instanceof AnchorPane) {
            for (Node node : parent.getChildren()) {
                if (node instanceof Pane tilePane) {
                    attachTileClickHandler(tilePane);
                    attachClickHandlers(tilePane);
                }
            }
        } else if (parent instanceof GridPane grid) {
            for (Node node : grid.getChildren()) {
                if (node instanceof Pane tilePane) {
                    attachTileClickHandler(tilePane);
                }
            }
        }
    }

    private void attachTileClickHandler(Pane tilePane) {
        tilePane.setOnMouseClicked(event -> showTileInfo(tilePane));
    }

    private void showTileInfo(Pane tilePane) {
        String tileId = tilePane.getId();
        if (tileId == null){
            return;
        }
        Tile tile = board.getTileByPosition(Integer.parseInt(tileId.replace("_","")));
        if (tile != null) {
            tile.accept(tileInfoPaneUpdater);
        }
    }
}