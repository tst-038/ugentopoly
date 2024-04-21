package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.StartTile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import be.ugent.objprog.ugentopoly.ui.UIUpdater;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager instance;
    private List<Player> players;
    private UIUpdater uiUpdater;
    private Map<Player, PlayerPion> playerPionMap;
    private AnchorPane rootPane;

    private PlayerManager(List<Player> players, UIUpdater uiUpdater, AnchorPane rootPane) {
        this.players = players;
        this.uiUpdater = uiUpdater;
        this.playerPionMap = new HashMap<>();
        this.rootPane = rootPane;
    }

    public static PlayerManager getInstance(List<Player> players, UIUpdater uiUpdater, AnchorPane rootPane) {
        if (instance == null) {
            instance = new PlayerManager(players, uiUpdater, rootPane);
        }
        return instance;
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    public void initializePlayers(AnchorPane rootPane) {
        uiUpdater.updatePlayers(players);
        players.forEach(player -> {
            // Makes sure the players start at the start tile.
            GameState.getInstance().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.START).map(StartTile.class::cast).findFirst()
                    .ifPresent(startTile -> players.forEach(p -> p.setInitialPosition(startTile.getPosition())));
            Pane pionContainer = (Pane) rootPane.lookup("#_" + player.getPosition()).lookup("#pionContainer");
            PlayerPion pion = new PlayerPion(player);
            player.setPion(pion);
            pion.addToContainer(pionContainer);
        });
    }

    public List<Player> getPlayers() {
        return players;
    }

    private PlayerPion getPlayerPion(Player player) {
        return playerPionMap.get(player);
    }

    public void setPlayerPanelToActive(Player player) {
        Node playerPane = BoardManager.getInstance().findPlayerNode(player, rootPane);
        if (playerPane != null) {
            if (!playerPane.getStyleClass().contains("activePlayerPanel")) {
                playerPane.getStyleClass().add("activePlayerPanel");
            }
            Node rollDiceButton = playerPane.lookup("#rollDiceButton");
            if (rollDiceButton != null) {
                rollDiceButton.setDisable(false);
            }
        }
    }

    public void setPlayerPanelToInactive(Player player) {
        Node playerPane = BoardManager.getInstance().findPlayerNode(player, rootPane);
        if (playerPane != null) {
            playerPane.getStyleClass().remove("activePlayerPanel");
        }
    }
}