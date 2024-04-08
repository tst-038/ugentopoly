package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exceptions.ui.UIUpdateException;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.UIUpdateVisitor;
import be.ugent.objprog.ugentopoly.ui.UIUpdateVisitorImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.List;

public class UIUpdater {
    private static UIUpdater instance = null;
    private final AnchorPane rootPane;

    private UIUpdater(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public static UIUpdater getInstance(AnchorPane rootPane) {
        if (instance == null) {
            instance = new UIUpdater(rootPane);
        }
        return instance;
    }

    public static UIUpdater getInstance() {
        return instance;
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

    public void updatePlayers(List<Player> players) {
        HBox playerPanel1 = (HBox) rootPane.lookup("#playerPanel1");
        HBox playerPanel2 = (HBox) rootPane.lookup("#playerPanel2");

        playerPanel1.getChildren().clear();
        playerPanel2.getChildren().clear();

        int panelIndex = 0;
        for (Player player : players) {
            try {
                FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/ui/playerpanel.fxml"));
                Node playerNode = loader.load();
                playerNode.setOpacity(1);

                playerNode.setId("player" + player.getName());
                Label playerName = (Label) playerNode.lookup("#playerName");
                playerName.setText(player.getName());
                playerName.setTextFill(player.getColor());

                Label playerBalance = (Label) playerNode.lookup("#playerBalance");
                playerBalance.textProperty().bind(player.balanceProperty().asString(Settings.getMoneyUnit() + "%d"));

                if (panelIndex < 2) {
                    playerPanel1.getChildren().add(playerNode);
                } else {
                    playerNode.setRotate(180);
                    playerPanel2.getChildren().add(playerNode);
                }

                panelIndex++;
            } catch (Exception e) {
                throw new UIUpdateException("Error updating UI for player " + player.getName(), e);
            }
        }
    }

    public void playerBoughtTile(Player player, Buyable tile){
        Label tileName = (Label) rootPane.lookup("#_" + tile.getPosition()).lookup("Label");
        tileName.setTextFill(player.getColor());
        tileName.setFont(Font.font(tileName.getFont().getFamily(), FontWeight.EXTRA_BOLD, tileName.getFont().getSize()+2));
    }
}