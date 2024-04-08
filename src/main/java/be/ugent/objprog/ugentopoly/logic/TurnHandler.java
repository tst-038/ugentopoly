package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Player;

public class TurnHandler implements GameOverListener {

    private static TurnHandler instance;
    private int currentPlayerIndex;
    private boolean gameOver;

    private TurnHandler() {
        currentPlayerIndex = 0;
    }

    public static TurnHandler getInstance() {
        if (instance == null) {
            instance = new TurnHandler();
        }
        return instance;
    }

    public void startGame() {
        // Initialize the game state and start the game loop
        while (!isGameOver()) {
            playTurn();
        }
        // Handle game over logic
    }

    public void playTurn() {
        Player currentPlayer = GameState.getInstance().getPlayers().get(currentPlayerIndex);
        // Perform actions for the current player's turn
        // For example:
        // 1. Roll the dice using DiceHandler
        //TODO enable dice button for player

        // 2. Move the player's token on the board
        // 3. Handle any special actions or events based on the player's position
        // 4. End the turn and move to the next player
        playTurn();
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