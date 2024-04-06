package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.exceptions.ui.UIUpdateException;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.UIUpdateVisitor;
import be.ugent.objprog.ugentopoly.ui.UIUpdateVisitorImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;

public class UIUpdater {
    private final AnchorPane rootPane;

    public UIUpdater(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public void colorAreaPanes(List<Area> areas) {
        for (Area area : areas) {
            String areaId = area.id();
            String areaClass = "area-" + areaId.substring(4);
            rootPane.lookupAll("." + areaClass).forEach(node -> {
                Pane areaPane = (Pane) node;
                areaPane.setStyle("-fx-background-color: " + area.color());
            });
        }
    }

    public void updateTiles(List<Tile> tiles) {
        UIUpdateVisitor visitor = new UIUpdateVisitorImpl();
        for (Tile tile : tiles) {
            try {
                Node node = rootPane.lookup("#_" + tile.getPosition());
                URL fxmlFileURL = tile.getFxmlURL();
                FXMLLoader loader = new FXMLLoader(fxmlFileURL);
                Node tileNode = loader.load();

                tileNode.setRotate(tile.getOrientation().getRotation());

                tile.acceptUIUpdate(visitor, tileNode, rootPane);

                rootPane.getChildren().add(tileNode);

                if (node instanceof GridPane grid) {
                    grid.add(tileNode, 0, 0);
                } else if (node instanceof Pane pane) {
                    pane.getChildren().add(tileNode);
                }
            } catch (Exception e) {
                throw new UIUpdateException("Error updating UI for tile " + tile.getId(), e);
            }
        }
    }
}