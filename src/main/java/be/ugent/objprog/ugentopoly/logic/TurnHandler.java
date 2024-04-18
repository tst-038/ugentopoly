package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.controller.PlayerManager;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PassedStartLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
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

    public static TurnHandler getInstance() {
        return instance;
    }

    public void startGame() {
        // Initialize the game state and start the game loop
        GameState.getInstance().addGameOverListener(this);
        DiceHandler.getInstance().addDiceRolledListener(this);
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }

    public Player getCurrentPlayer() {
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
        if (newPosition < player.getPosition()) {
            // Player has passed the start tile, give them the start bonus
            Bank.getInstance().addMoney(player, Settings.getInstance().getStartBonus());
            GameLogBook.getInstance().addEntry(new PassedStartLog(player));
        }

        player.useGetOutOfJailFreeCard();

       if(player.getRemainingTurnsInPrison() > 0){
            if (rolls.get(0).equals(rolls.get(1))) {
                player.resetRemainingTurnsInPrison();
                player.setPosition(newPosition);
            } else {
                player.decreaseRemainingTurnsInPrison();
            }
        } else {
            player.setPosition(newPosition);
        }

        // Trigger the onLand event for the landed tile
        Tile landedTile = GameState.getInstance().getBoard().getTileByPosition(newPosition);
        landedTile.onVisit(player);

        // Check if the player rolled a double if not disable the current player and enable the next player
        if (!rolls.getFirst().equals(rolls.get(1))) {
            playerManager.setPlayerPanelToInactive(getCurrentPlayer());
            currentPlayerIndex = (currentPlayerIndex + 1) % playerManager.getPlayers().size();
            playerManager.setPlayerPanelToActive(getCurrentPlayer());
        }

        // Player has rolled double reenable roll button
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }
}