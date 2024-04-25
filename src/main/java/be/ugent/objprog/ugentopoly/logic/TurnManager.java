package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.controller.GameOverController;
import be.ugent.objprog.ugentopoly.ui.manager.PlayerManager;
import be.ugent.objprog.ugentopoly.logic.handler.DiceRolledHandler;
import be.ugent.objprog.ugentopoly.logic.listener.DiceRolledListener;
import be.ugent.objprog.ugentopoly.logic.listener.GameOverListener;
import be.ugent.objprog.ugentopoly.model.player.Player;

import java.util.List;

public class TurnManager implements GameOverListener, DiceRolledListener {
    private final DiceRolledHandler diceRolledEventHandler;
    private final GameManager gameManager;
    private int currentPlayerIndex;
    private final PlayerManager playerManager;
    private final GameOverController gameOverController;

    public TurnManager(GameManager gameManager, GameOverController gameOverController) {
        this.diceRolledEventHandler = new DiceRolledHandler(gameManager, this);
        this.gameManager = gameManager;
        this.playerManager = gameManager.getPlayerManager();
        this.currentPlayerIndex = 0;
        this.gameOverController = gameOverController;
    }

    public void initialize() {
        gameManager.getGameState().addGameOverListener(this);
        gameManager.getDiceHandler().addDiceRolledListener(this);
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }

    public Player getCurrentPlayer() {
        return playerManager.getPlayers().get(currentPlayerIndex);
    }

    public void nextPlayer() {
        playerManager.setPlayerPanelToInactive(getCurrentPlayer());
        currentPlayerIndex = (currentPlayerIndex + 1) % playerManager.getPlayers().size();
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }

    public void continueTurn() {
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }

    @Override
    public void onGameOver(Player player) {
        gameOverController.showGameOverAlert();
    }

    @Override
    public void onDiceRolled(Player player, List<Integer> rolls) {
        diceRolledEventHandler.onDiceRolled(player, rolls);
    }
}