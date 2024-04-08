package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import be.ugent.objprog.ugentopoly.ui.util.UIUpdater;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerManager {
    private List<Player> players;
    private AnchorPane rootPane;
    private UIUpdater uiUpdater;
    private Map<Player, PlayerPion> playerPionMap;

    public PlayerManager(List<Player> players, AnchorPane rootPane, UIUpdater uiUpdater) {
        this.players = players;
        this.rootPane = rootPane;
        this.uiUpdater = uiUpdater;
        this.playerPionMap = new HashMap<>();
    }

    public void initializePlayers() {
        uiUpdater.updatePlayers(players);
        initializePlayerPions();
    }

    private void initializePlayerPions() {
        for (Player player : players) {
            Image pionImage = new Image(Objects.requireNonNull(ResourceLoader.loadResource("assets/token" + (playerPionMap.size() + 1) + ".png")));
            HBox pionContainer = (HBox) rootPane.lookup("#_1").lookup("#pionContainer");
            PlayerPion playerPion = new PlayerPion(player, pionImage, pionContainer);
            playerPionMap.put(player, playerPion);
            pionContainer.toFront();
        }
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

    private PlayerPion getPlayerPion(Player player) {
        return playerPionMap.get(player);
    }
}