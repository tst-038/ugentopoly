// UIUpdater.java
package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exceptions.ui.UIUpdateException;
import be.ugent.objprog.ugentopoly.logic.DiceHandler;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.List;

public class UIUpdater {
    private static UIUpdater instance;
    private AnchorPane rootPane;

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

        int panelIndex = 0;
        for (Player player : players) {
            try {
                FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/ui/playerpanel.fxml"));
                Node playerNode = loader.load();

                playerNode.setId("player_" + player.getId());
                Label playerName = (Label) playerNode.lookup("#playerName");
                playerName.setText(player.getName());
                playerName.setTextFill(player.getColor());

                Label playerBalance = (Label) playerNode.lookup("#playerBalance");
                playerBalance.textProperty().bind(player.balanceProperty().asString(Settings.getMoneyUnit() + "%d"));
                Label playerNetworth = (Label) playerNode.lookup("#playerNetworth");
                playerNetworth.textProperty().bind(player.networthProperty().asString(Settings.getMoneyUnit() + "%d"));

                Button rollDiceButton = (Button) playerNode.lookup("#rollDiceButton");
                rollDiceButton.setOnAction(event -> {
                    rollDiceButton.setDisable(true);
                    DiceHandler.getInstance().rollDice(player);
                });

                if (panelIndex % 2 == 0) {
                    playerPanel1.getChildren().addLast(playerNode);
                } else {
                    playerNode.setRotate(180);
                    playerPanel2.getChildren().addLast(playerNode);
                }



                panelIndex++;
            } catch (Exception e) {
                throw new UIUpdateException("Error updating UI for player " + player.getName(), e);
            }
        }
    }



    public void playerBoughtTile(Player player, Buyable tile){
        Pane tilePane = (Pane) rootPane.lookup("#_" + tile.getPosition());
        Label tileName = (Label) tilePane.lookup("Label");
        if(tileName == null){
            tilePane.setBorder(new Border(new BorderStroke(player.getColor(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,4,0))));
            return;
        }
        tileName.setTextFill(player.getColor());
        tileName.setFont(Font.font(tileName.getFont().getFamily(), FontWeight.EXTRA_BOLD, tileName.getFont().getSize()+2));
    }
}