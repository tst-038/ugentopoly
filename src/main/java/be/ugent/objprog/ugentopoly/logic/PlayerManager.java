package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import be.ugent.objprog.ugentopoly.ui.UIUpdater;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerManager {
    private static PlayerManager instance;
    private List<Player> players;
    private AnchorPane rootPane;
    private UIUpdater uiUpdater;
    private Map<Player, PlayerPion> playerPionMap;

    private PlayerManager(List<Player> players, AnchorPane rootPane, UIUpdater uiUpdater) {
        this.players = players;
        this.rootPane = rootPane;
        this.uiUpdater = uiUpdater;
        this.playerPionMap = new HashMap<>();
    }

    public static PlayerManager getInstance(List<Player> players, AnchorPane rootPane, UIUpdater uiUpdater) {
        if (instance == null) {
            instance = new PlayerManager(players, rootPane, uiUpdater);
        }
        return instance;
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    public void initializePlayers() {
        uiUpdater.updatePlayers(players);
        players.forEach(player -> {
            player.initializePion();
        });
    }


// TODO remove this
//    public void movePlayerPion(Player player, int steps) {
//        PlayerPion playerPion = getPlayerPion(player);
//        if (playerPion != null) {
//            int newPosition = (player.getPosition() + steps) % .getTiles().size();
//            player.setPosition(newPosition);
//
//            Tile newTile = board.getTileByPosition(newPosition);
//            HBox newPionContainer = (HBox) rootPane.lookup("#_" + newTile.getPosition()).lookup("#pionContainer");
//
//            TranslateTransition transition = new TranslateTransition(Duration.millis(500), playerPion);
//            transition.setToX(newPionContainer.getLayoutX() - playerPion.getLayoutX());
//            transition.setToY(newPionContainer.getLayoutY() - playerPion.getLayoutY());
//            transition.setOnFinished(e -> {
//                playerPion.pionContainer.getChildren().remove(playerPion);
//                newPionContainer.getChildren().add(playerPion);
//                playerPion.pionContainer = newPionContainer;
//            });
//            transition.play();
//        }
//    }

    public List<Player> getPlayers() {
        return players;
    }

    private PlayerPion getPlayerPion(Player player) {
        return playerPionMap.get(player);
    }

    public void setPlayerPanelToActive(Player player) {
        Node playerPane = BoardManager.getInstance().findPlayerNode(player);
        if (playerPane != null) {
            playerPane.getStyleClass().add("activePlayerPanel");
            Node rollDiceButton = playerPane.lookup("#rollDiceButton");
            if (rollDiceButton != null) {
                rollDiceButton.setDisable(false);
            }
        }
    }

    public void setPlayerPanelToInactive(Player player) {
        Node playerPane = BoardManager.getInstance().findPlayerNode(player);
        if (playerPane != null) {
            playerPane.getStyleClass().remove("activePlayerPanel");
        }
    }
}