package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Player;

public class TurnHandler implements GameOverListener {

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
        playerManager.enableButtonForPlayer(getCurrentPlayer());
        while (!isGameOver()) {
            // Wait for the player to take their turn
        }
        // Handle game over logic
    }

    public void playTurn() {
        Player currentPlayer = getCurrentPlayer();
        // Perform actions for the current player's turn
        // ...

        // End the turn and move to the next player
        playerManager.disableButtonForPlayer(currentPlayer);
        currentPlayerIndex = (currentPlayerIndex + 1) % GameState.getInstance().getPlayers().size();
        playerManager.enableButtonForPlayer(getCurrentPlayer());
    }

    private Player getCurrentPlayer() {
        return GameState.getInstance().getPlayers().get(currentPlayerIndex);
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
}