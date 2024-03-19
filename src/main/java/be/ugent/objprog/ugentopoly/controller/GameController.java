package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneUpdater;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Set;

public class GameController {
    private Board board;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private HBox  top;

    @FXML
    private HBox bottom;

    @FXML
    private VBox left;

    @FXML
    private VBox right;

    @FXML
    private AnchorPane tileInfoPane;


    private TileInfoPaneUpdater tileInfoPaneUpdater;



    public void setBoard(Board board) {
        this.board = board;
        tileInfoPaneUpdater = new TileInfoPaneUpdater(tileInfoPane, board.getSettings());
        colorAreaPanes();
        updateTileLabels();
        // todo put in list
        attachClickHandlers(top);
        attachClickHandlers(bottom);
        attachClickHandlers(left);
        attachClickHandlers(right);
    }


    private void colorAreaPanes() {
        for (Area area : board.getAreas()) {
            String areaId = area.getId();
            // turns areaid into area class
            String areaClass = "area-" + areaId.substring(4);
            rootPane.lookupAll("." + areaClass).forEach(node -> {
                Pane areaPane = (Pane) node;
                areaPane.setStyle("-fx-background-color: " + area.getColor());
            });
        }
    }

    private void updateTileLabels() {
        for (Tile tile : board.getTiles()) {
            String tileId = tile.getId();
            Set<Node> nodes = rootPane.lookupAll("#" + tileId);
            for (Node node : nodes) {
                Pane tilePane = (Pane) node;
                Label tileLabel = (Label) tilePane.lookup("Label");
                if (tileLabel != null) {
                    tileLabel.setText(tile.getName());
                    tileLabel.setFont(Font.font("System", FontWeight.SEMI_BOLD, 14));
                    tileLabel.setWrapText(true);
                    tileLabel.setAlignment(Pos.CENTER);
                    tileLabel.setTextAlignment(TextAlignment.CENTER);
                }
            }
        }
    }

    private void attachClickHandlers(Pane parent) {
        if (parent instanceof GridPane grid) {
            for (Node node : grid.getChildren()) {
                if (node instanceof Pane tilePane) {
                    attachClickHandler(tilePane);
                }
            }
        } else if (parent instanceof HBox || parent instanceof VBox || parent instanceof AnchorPane) {
            for (Node node : parent.getChildren()) {
                if (node instanceof Pane tilePane) {
                    attachClickHandler(tilePane);
                }
                if (node instanceof Pane nestedPane) {
                    attachClickHandlers(nestedPane);
                }
            }
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