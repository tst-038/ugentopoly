// UIUpdater.java
package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.exceptions.ui.UIUpdateException;
import be.ugent.objprog.ugentopoly.logic.DiceHandler;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
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
    private final Game game;
    private final AnchorPane rootPane;
    private final DiceHandler diceHandler;
    private final PropertyReader propertyReader;

    public UIUpdater(AnchorPane rootPane, Game game) {
        this.game = game;
        this.rootPane = rootPane;
        this.diceHandler = game.getDiceHandler();
        this.propertyReader = game.getPropertyreader();
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

    public void updateTiles(List<Tile> tiles) {
        UIUpdateVisitor visitor = new UIUpdateVisitorImpl();
        for (Tile tile : tiles) {
            try {
                Node node = rootPane.lookup("#_" + tile.getPosition());
                URL fxmlFileURL = tile.getFxmlURL();
                FXMLLoader loader = new FXMLLoader(fxmlFileURL);
                Node tileNode = loader.load();

                if (tileNode instanceof Group tileGroup){
                    tileGroup.getChildren().getFirst().setRotate(tile.getOrientation().getRotation());
                }


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

                Label playerBalanceLabel = (Label) playerNode.lookup("#playerBalanceLabel");
                playerBalanceLabel.setText(propertyReader.get("label.balance"));

                Label playerBalance = (Label) playerNode.lookup("#playerBalance");
                playerBalance.textProperty().bind(player.balanceProperty().asString(game.getSettings().getMoneyUnit() + "%d"));

                Label playerNetworthLabel = (Label) playerNode.lookup("#playerNetworthLabel");
                playerNetworthLabel.setText(propertyReader.get("label.networth"));
                Label playerNetworth = (Label) playerNode.lookup("#playerNetworth");
                playerNetworth.textProperty().bind(player.networthProperty().asString(game.getSettings().getMoneyUnit() + "%d"));

                Button rollDiceButton = (Button) playerNode.lookup("#rollDiceButton");
                rollDiceButton.setText(propertyReader.get("button.roll_dice"));
                rollDiceButton.setOnAction(event -> {
                    rollDiceButton.setDisable(true);
                    diceHandler.rollDice(player);
                });

                ImageView pionImage = (ImageView) playerNode.lookup("#pion");
                pionImage.setImage(player.getPion().getImage());

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

    public void initializeDices(Node diceDialog) {
        AnchorPane dicePane = (AnchorPane) rootPane.lookup("#dicePane");
        dicePane.getChildren().add(diceDialog);
        AnchorPane.setTopAnchor(diceDialog, 0.0);
        AnchorPane.setLeftAnchor(diceDialog, 0.0);
        AnchorPane.setRightAnchor(diceDialog, 0.0);
        AnchorPane.setBottomAnchor(diceDialog, 0.0);
    }

    public void playerBoughtTile(Player player, Buyable tile) {
        Pane tilePane = (Pane) rootPane.lookup("#_" + tile.getPosition());
        Label tileName = (Label) tilePane.lookup("Label");

        if (tileName == null) {
            ((AnchorPane) tilePane.lookup("#ownerColor")).setBackground(new Background(new BackgroundFill(player.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
            return;
        }
        tileName.setTextFill(player.getColor());
        tileName.setFont(Font.font(tileName.getFont().getFamily(), FontWeight.EXTRA_BOLD, tileName.getFont().getSize() + 2));
    }

    public void bindJackpot() {
        Label jackpotLabel = (Label) rootPane.lookup("#jackpot");
        jackpotLabel.textProperty().bind(game.getBank().getJackpotBalanceProperty().asString(game.getSettings().getMoneyUnit() + "%d"));
    }
}