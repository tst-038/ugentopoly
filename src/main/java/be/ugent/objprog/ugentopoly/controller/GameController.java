package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneUpdater;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameController {
    private Board board;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane leftGrid;

    @FXML
    private GridPane rightGrid;

    @FXML
    private GridPane topGrid;

    @FXML
    private GridPane bottomGrid;

    @FXML
    private AnchorPane topLeftCorner;

    @FXML
    private AnchorPane topRightCorner;

    @FXML
    private Pane tile0;

    @FXML
    private AnchorPane bottomRightCorner;

    @FXML
    private AnchorPane tileInfoPane;
    @FXML
    private Label infoTitle;
    @FXML
    private Pane infoColor;
    @FXML
    private Label rent;
    @FXML
    private Label price;
    @FXML
    private Label owner;

    @FXML
    private Label playerInfoLabel;


    private TileInfoPaneUpdater tileInfoPaneUpdater;

    @FXML
    public void initialize() {
        tileInfoPaneUpdater = new TileInfoPaneUpdater(tileInfoPane);
    }


    public void setBoard(Board board) {
        this.board = board;
        attachClickHandlers(leftGrid);
        attachClickHandlers(rightGrid);
        attachClickHandlers(topGrid);
        attachClickHandlers(bottomGrid);
        attachClickHandlers(topLeftCorner);
        attachClickHandlers(topRightCorner);
        attachClickHandlers(tile0);
        attachClickHandlers(bottomRightCorner);
    }

    private void attachClickHandlers(Pane parent) {
        if (parent instanceof GridPane) {
            GridPane grid = (GridPane) parent;
            for (Node node : grid.getChildren()) {
                if (node instanceof Pane) {
                    Pane tilePane = (Pane) node;
                    attachClickHandler(tilePane);
                }
            }
        } else if (parent instanceof AnchorPane) {
            attachClickHandler(parent);
        }
    }

    private void attachClickHandler(Pane tilePane) {
        tilePane.setOnMouseEntered(event -> {
            String tileId = tilePane.getId();
            Tile tile = board.getTileById(tileId);
            if (tile != null) {
                showTileInfo(tile);
            }
        });
        tilePane.setOnMouseExited(event -> {
            String tileId = tilePane.getId();
            Tile tile = board.getTileById(tileId);
            if (tile != null) {
                showTileInfo(tile);
            }
        });
    }

    private void showTileInfo(Tile tile) {
        tile.accept(tileInfoPaneUpdater);
    }

}