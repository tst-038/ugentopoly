package be.ugent.objprog.ugentopoly.ui.manager;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.StartTile;
import be.ugent.objprog.ugentopoly.model.tile.TileType;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class PlayerManager {
    private static final String ACTIVE_PLAYER_PANEL = "activePlayerPanel";
    private final GameManager gameManager;
    private final List<Player> players;
    private final UIUpdater uiUpdater;
    private final AnchorPane rootPane;

    public PlayerManager(GameManager gameManager, UIUpdater uiUpdater, AnchorPane rootPane) {
        this.gameManager = gameManager;
        this.players = gameManager.getPlayers();
        this.uiUpdater = uiUpdater;
        this.rootPane = rootPane;
    }

    public void initializePlayers(AnchorPane rootPane) {
        players.forEach(player -> {
            gameManager.getGameState().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.START).map(StartTile.class::cast).findFirst()
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
        Node playerPane = gameManager.getBoardManager().findPlayerNode(player, rootPane);
        if (playerPane != null) {
            if (!playerPane.getStyleClass().contains(ACTIVE_PLAYER_PANEL)) {
                playerPane.getStyleClass().add(ACTIVE_PLAYER_PANEL);
            }
            Node rollDiceButton = playerPane.lookup("#rollDiceButton");
            if (rollDiceButton != null) {
                rollDiceButton.setDisable(false);
            }
        }
    }

    public void setPlayerPanelToInactive(Player player) {
        Node playerPane = gameManager.getBoardManager().findPlayerNode(player, rootPane);
        if (playerPane != null) {
            playerPane.getStyleClass().remove(ACTIVE_PLAYER_PANEL);
        }
    }
}