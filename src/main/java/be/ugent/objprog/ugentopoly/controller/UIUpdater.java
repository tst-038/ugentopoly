package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.exceptions.ui.UIUpdateException;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
        for (Tile tile : tiles) {
            try {
                Node node = rootPane.lookup("#_" + tile.getPosition());
                URL fxmlFileURL = tile.getFxmlURL();
                FXMLLoader loader = new FXMLLoader(fxmlFileURL);
                Node tileNode = loader.load();

                tileNode.setRotate(tile.getOrientation().getRotation());

                Label label = null;
                if(tile.getType() != TileType.UTILITY){
                    label = (Label) tileNode.lookup("Label");
                    label.setText(tile.getName());
                }

                if (tile.getType() == TileType.STREET) {
                    StreetTile streetTile = (StreetTile) tile;
                    tileNode.lookup("#area").setStyle("-fx-background-color: " + streetTile.getArea().color());
                }else if(tile.getType() == TileType.CHANCE || tile.getType() == TileType.RAILWAY || tile.getType() == TileType.TAX || tile.getType() == TileType.CHEST){
                    ImageView imageView = new ImageView(tile.getImage());
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setPreserveRatio(true);
                    imageView.setPickOnBounds(true);
                    assert label != null;
                    label.setGraphic(imageView);
                } else if (tile.getType() == TileType.JAIL || tile.getType() == TileType.GO_TO_JAIL || tile.getType() == TileType.FREE_PARKING ){
                    ImageView imageView = new ImageView(tile.getImage());
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setPreserveRatio(true);
                    imageView.setPickOnBounds(true);
                    assert label != null;
                    label.setGraphic(imageView);
                }else if (tile.getType() == TileType.UTILITY) {
                    ImageView image = (ImageView) tileNode.lookup("ImageView");
                    image.setImage(tile.getImage());
                }

                rootPane.getChildren().add(tileNode);

                if(node instanceof GridPane grid){
                    grid.add(tileNode, 0, 0);
                }else if (node instanceof Pane pane) {
                    pane.getChildren().add(tileNode);
                }
            }catch (Exception e) {
                throw new UIUpdateException("Error updating UI for tile " + tile.getId(), e);
            }
        }
    }
}