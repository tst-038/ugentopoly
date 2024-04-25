package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.controller.GameOverController;
import be.ugent.objprog.ugentopoly.controller.PlayerManager;
import be.ugent.objprog.ugentopoly.log.PassedStartLog;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

import java.util.List;
import java.util.Optional;

public class TurnHandler implements GameOverListener, DiceRolledListener {
    private final DiceRolledEventHandler diceRolledEventHandler;
    private final Game game;
    private int currentPlayerIndex;
    private final PlayerManager playerManager;
    private final GameOverController gameOverController;

    public TurnHandler(Game game, GameOverController gameOverController) {
        this.diceRolledEventHandler = new DiceRolledEventHandler(game, this);
        this.game = game;
        this.playerManager = game.getPlayerManager();
        this.currentPlayerIndex = 0;
        this.gameOverController = gameOverController;
    }

    public void initialize() {
        game.getGameState().addGameOverListener(this);
        game.getDiceHandler().addDiceRolledListener(this);
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }

    public Player getCurrentPlayer() {
        return playerManager.getPlayers().get(currentPlayerIndex);
    }

    public void nextPlayer() {
        System.out.println("\n");
        playerManager.setPlayerPanelToInactive(getCurrentPlayer());
        System.out.println("Next player"+ getCurrentPlayer().getName());
        currentPlayerIndex = (currentPlayerIndex + 1) % playerManager.getPlayers().size();
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
        System.out.println("new active player"+ getCurrentPlayer().getName());
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