package be.ugent.objprog.ugentopoly.controller;

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
    private final Game game;
    private final List<Player> players;
    private final UIUpdater uiUpdater;
    private final Map<Player, PlayerPion> playerPionMap;
    private final AnchorPane rootPane;

    public PlayerManager(Game game, UIUpdater uiUpdater, AnchorPane rootPane) {
        this.game = game;
        this.players = game.getPlayers();
        this.uiUpdater = uiUpdater;
        this.playerPionMap = new HashMap<>();
        this.rootPane = rootPane;
    }

    public void initializePlayers(AnchorPane rootPane) {
        players.forEach(player -> {
            // Makes sure the players start at the start tile.
            game.getGameState().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.START).map(StartTile.class::cast).findFirst()
                    .ifPresent(startTile -> players.forEach(p -> p.getPosition().setInitialPosition(startTile.getPosition())));
            Pane pionContainer = (Pane) rootPane.lookup("#_" + player.getPosition().getPos()).lookup("#pionContainer");
            player.getPion().addToContainer(pionContainer);
        });
        uiUpdater.updatePlayers(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayerPanelToActive(Player player) {
        Node playerPane = game.getBoardManager().findPlayerNode(player, rootPane);
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
        Node playerPane = game.getBoardManager().findPlayerNode(player, rootPane);
        if (playerPane != null) {
            playerPane.getStyleClass().remove("activePlayerPanel");
        }
    }
}