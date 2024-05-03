package be.ugent.objprog.ugentopoly.ui.updater;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;
import be.ugent.objprog.ugentopoly.exception.ui.UIUpdateException;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class PlayerPanelUpdater {
    private final AnchorPane rootPane;
    private final GameManager gameManager;
    private final PropertyReader propertyReader;

    public PlayerPanelUpdater(AnchorPane rootPane, GameManager gameManager, PropertyReader propertyReader) {
        this.rootPane = rootPane;
        this.gameManager = gameManager;
        this.propertyReader = propertyReader;
    }

    public void updatePlayers(List<Player> players) {
        HBox playerPanel1 = (HBox) rootPane.lookup("#playerPanel1");
        HBox playerPanel2 = (HBox) rootPane.lookup("#playerPanel2");

        int panelIndex = 0;
        for (Player player : players) {
            try {
                Node playerNode = loadPlayerNode(player);
                updatePlayerLabels(playerNode, player);
                addRollDiceButtonAction(playerNode, player);
                setPawnImage(playerNode, player);
                addPlayerNodeToPanel(playerNode, panelIndex, playerPanel1, playerPanel2);
                panelIndex++;
            } catch (Exception e) {
                throw new UIUpdateException("Error updating UI for player " + player.getName(), e);
            }
        }
    }

    private Node loadPlayerNode(Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/ui/playerpanel.fxml"));
        Node playerNode = loader.load();
        playerNode.setId("player_" + player.getId());
        return playerNode;
    }

    private void updatePlayerLabels(Node playerNode, Player player) {
        Label playerName = (Label) playerNode.lookup("#playerName");
        playerName.setText(player.getName());
        playerName.setTextFill(player.getColor());

        Label playerBalanceLabel = (Label) playerNode.lookup("#playerBalanceLabel");
        playerBalanceLabel.setText(propertyReader.get("label.balance"));

        Label playerBalance = (Label) playerNode.lookup("#playerBalance");
        playerBalance.textProperty().bind(player.balanceProperty().asString(gameManager.getSettings().getMoneyUnit() + "%d"));

        Label playerNetworthLabel = (Label) playerNode.lookup("#playerNetworthLabel");
        playerNetworthLabel.setText(propertyReader.get("label.networth"));

        Label playerNetworth = (Label) playerNode.lookup("#playerNetworth");
        playerNetworth.textProperty().bind(player.networthProperty().asString(gameManager.getSettings().getMoneyUnit() + "%d"));
    }

    private void addRollDiceButtonAction(Node playerNode, Player player) {
        Button rollDiceButton = (Button) playerNode.lookup("#rollDiceButton");
        rollDiceButton.setText(propertyReader.get("button.roll_dice"));
        rollDiceButton.setOnAction(event -> {
            rollDiceButton.setDisable(true);
            gameManager.getTileInfoPaneManager().clearTileSelection();
            gameManager.getDiceHandler().rollDice(player);
        });
    }

    private void setPawnImage(Node playerNode, Player player) {
        ImageView pawnImage = (ImageView) playerNode.lookup("#pawn");
        pawnImage.setImage(player.getPawn().getImage());
    }

    private void addPlayerNodeToPanel(Node playerNode, int panelIndex, HBox playerPanel1, HBox playerPanel2) {
        if (panelIndex % 2 == 0) {
            playerPanel1.getChildren().addLast(playerNode);
        } else {
            playerNode.setRotate(180);
            playerPanel2.getChildren().addLast(playerNode);
        }
    }
}
