package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.Set;

public class UIUpdater {
    private final AnchorPane rootPane;

    public UIUpdater(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public void colorAreaPanes(List<Area> areas) {
        for (Area area : areas) {
            String areaId = area.getId();
            String areaClass = "area-" + areaId.substring(4);
            rootPane.lookupAll("." + areaClass).forEach(node -> {
                Pane areaPane = (Pane) node;
                areaPane.setStyle("-fx-background-color: " + area.getColor());
            });
        }
    }

    public void updateTileLabels(List<Tile> tiles) {
        for (Tile tile : tiles) {
            String tileId = tile.getId();
            Set<Node> nodes = rootPane.lookupAll("#" + tileId);
            for (Node node : nodes) {
                Pane tilePane = (Pane) node;
                Label tileLabel = (Label) tilePane.lookup("Label");
                if (tileLabel != null) {
                    tileLabel.setText(tile.getName());
                    tileLabel.getStyleClass().add("tile-label");
                }
            }
        }
    }
}