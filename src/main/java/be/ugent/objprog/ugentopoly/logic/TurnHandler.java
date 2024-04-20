package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.controller.BoardManager;
import be.ugent.objprog.ugentopoly.controller.GameOverController;
import be.ugent.objprog.ugentopoly.controller.PlayerManager;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PassedStartLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        GameOverController.showGameOverAlert();
    }

    @Override
    public void onDiceRolled(Player player, List<Integer> rolls) {
        int diceResult = rolls.stream().mapToInt(Integer::intValue).sum();
        boolean hasRolledDouble = rolls.get(0).equals(rolls.get(1));

        // If the player rolled a double, increment their double rolls count
        if (hasRolledDouble) {
            player.addDoubleRoll();
            // If the player has rolled doubles three times, send them to jail
            //TODO put remainingTurnsInPrison in settings
            if (player.getDoubleRolls() == 3) {
                player.setRemainingTurnsInPrison(3);
                player.resetDoubleRolls();
                Optional<Tile> jailTile = GameState.getInstance().getBoard().getTiles().stream()
                        .filter(tile -> tile.getType() == TileType.JAIL)
                        .findFirst();
                jailTile.ifPresent(tile -> player.setPosition(tile.getPosition()));

                playerManager.setPlayerPanelToInactive(getCurrentPlayer());
                currentPlayerIndex = (currentPlayerIndex + 1) % playerManager.getPlayers().size();
                playerManager.setPlayerPanelToActive(getCurrentPlayer());
                //skip rest of the turn
                return;
            }
        } else {
            // If the player didn't roll a double, reset their double rolls count
            player.resetDoubleRolls();
        }

        // Move the player's position
        int newPosition = (player.getPosition() + diceResult) % GameState.getInstance().getBoard().getTiles().size();
        if (newPosition < player.getPosition()) {
            // Player has passed the start tile, give them the start bonus
            Bank.getInstance().addMoney(player, Settings.getInstance().getStartBonus());
            GameLogBook.getInstance().addEntry(new PassedStartLog(player));
        }

        Tile landedTile = GameState.getInstance().getBoard().getTiles().stream()
                .filter(tile -> tile.getPosition() == newPosition)
                .findFirst().get();

        if (landedTile.getType() == TileType.JAIL && player.getRemainingTurnsInPrison() > 0) {
            player.useGetOutOfJailFreeCard();
        }

       if(player.getRemainingTurnsInPrison() > 0){
            if (hasRolledDouble) {
                player.resetRemainingTurnsInPrison();
                player.setPosition(newPosition);
            } else {
                player.decreaseRemainingTurnsInPrison();
            }
        } else {
            player.setPosition(newPosition);
        }



        // Check if the player rolled a double if not disable the current player and enable the next player
        // Check if the player landed on the go to jail tile
        // if so go on to next player without letting throw second time
        boolean landedTileIsGoToJail = GameState.getInstance().getBoard().getTiles().stream().anyMatch(tile -> tile.getPosition() == newPosition && tile.getType() == TileType.GO_TO_JAIL);
        if (!hasRolledDouble || landedTileIsGoToJail){
            playerManager.setPlayerPanelToInactive(getCurrentPlayer());
            currentPlayerIndex = (currentPlayerIndex + 1) % playerManager.getPlayers().size();
            playerManager.setPlayerPanelToActive(getCurrentPlayer());
        }

        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }
}