package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;

import java.util.List;

public class TurnHandler implements GameOverListener, DiceRolledListener {

    private static TurnHandler instance;
    private int currentPlayerIndex;
    private boolean gameOver;
    private PlayerManager playerManager;

    private TurnHandler(PlayerManager playerManager) {
        currentPlayerIndex = 0;
        this.playerManager = playerManager;
    }

    public static TurnHandler getInstance(PlayerManager playerManager) {
        if (instance == null) {
            instance = new TurnHandler(playerManager);
        }
        return instance;
    }

    public void startGame() {
        // Initialize the game state and start the game loop
        GameState.getInstance().addGameOverListener(this);
        DiceHandler.getInstance().addDiceRolledListener(this);
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }

    private Player getCurrentPlayer() {
        return playerManager.getPlayers().get(currentPlayerIndex);
    }

    private boolean isGameOver() {
       return gameOver;
    }


    @Override
    public void onGameOver(Player player) {
        gameOver = true;
        System.out.println("Game over! Player " + player.getName() + " has run out of money.");
        // Handle game over logic, such as displaying a message or ending the game
    }

    @Override
    public void onDiceRolled(Player player, List<Integer> rolls) {
        int diceResult = rolls.stream().mapToInt(Integer::intValue).sum();

        // Move the player's position
        int newPosition = (player.getPosition() + diceResult) % GameState.getInstance().getBoard().getTiles().size();
        player.setPosition(newPosition);

        // Trigger the onLand event for the landed tile
        Tile landedTile = GameState.getInstance().getBoard().getTileByPosition(newPosition);
        landedTile.onVisit(player);

        playerManager.setPlayerPanelToInactive(getCurrentPlayer());
        currentPlayerIndex = (currentPlayerIndex + 1) % playerManager.getPlayers().size();
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }
}