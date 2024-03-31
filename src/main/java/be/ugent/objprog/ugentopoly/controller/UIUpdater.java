package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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

    // TODO refractor to make use of position instead of tile id
    public void updateTileLabels(List<Tile> tiles) {
        for (Tile tile : tiles) {
            Set<Node> nodes = rootPane.lookupAll("#_" + tile.getPosition());
            for (Node node : nodes) {
                Pane tilePane = (Pane) node;
                Label tileLabel = (Label) tilePane.lookup("Label");
                if (tileLabel != null) {
                    tileLabel.setText(tile.getName());
                    tileLabel.getStyleClass().add("tile-label");
                }
                if (tile.getType() == TileType.STREET){
                    StreetTile streetTile = (StreetTile) tile;
                    Node areaNode = node.lookup("#area");
                    if (areaNode != null){
                        Pane areaPane = (Pane) areaNode;
                        areaPane.setStyle("-fx-background-color: "+ streetTile.getArea().getColor());
                    }
                }
            }
        }
    }
}