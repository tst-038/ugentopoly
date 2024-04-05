package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileConfig;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

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
    public void updateTiles(List<Tile> tiles) {
        for (Tile tile : tiles) {
            try {
                Node node = rootPane.lookup("#_" + tile.getPosition());
                String fxmlFileName = TileConfig.getFXMLFileName(tile.getType(), tile.getOrientation());
                FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/tiles/"+fxmlFileName));
                Node tileNode = loader.load();

                tileNode.setRotate(tile.getOrientation().getRotation(tile.getType()));

                Label label = null;
                if(tile.getType() != TileType.UTILITY){
                    label = (Label) tileNode.lookup("Label");
                    label.setText(tile.getName());
                }

                if (tile.getType() == TileType.STREET) {
                    StreetTile streetTile = (StreetTile) tile;
                    tileNode.lookup("#area").setStyle("-fx-background-color: " + streetTile.getArea().getColor());
                }else if(tile.getType() == TileType.CHANCE || tile.getType() == TileType.RAILWAY || tile.getType() == TileType.TAX || tile.getType() == TileType.CHEST){
                    ImageView imageView = new ImageView(tile.getImage());
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setPreserveRatio(true);
                    //TODO needed?
                    imageView.setPickOnBounds(true);
                    label.setGraphic(imageView);
                } else if (tile.getType() == TileType.JAIL || tile.getType() == TileType.GO_TO_JAIL || tile.getType() == TileType.FREE_PARKING ){
                    ImageView imageView = new ImageView(tile.getImage());
                    imageView.setFitHeight(55);
                    imageView.setFitWidth(55);
                    imageView.setPreserveRatio(true);
                    imageView.setPickOnBounds(true);
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
                e.printStackTrace();
            }
        }
    }
}