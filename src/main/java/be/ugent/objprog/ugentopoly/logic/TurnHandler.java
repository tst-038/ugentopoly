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
    private final Game game;
    private int currentPlayerIndex;
    private boolean gameOver;
    private final PlayerManager playerManager;
    private final GameOverController gameOverController;

    public TurnHandler(Game game, PlayerManager playerManager, GameOverController gameOverController) {
        this.game = game;
        this.currentPlayerIndex = 0;
        this.playerManager = playerManager;
        this.gameOverController = gameOverController;
    }

    public void startGame() {
        game.getGameState().addGameOverListener(this);
        game.getDiceHandler().addDiceRolledListener(this);
        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }

    public Player getCurrentPlayer() {
        return playerManager.getPlayers().get(currentPlayerIndex);
    }

    @Override
    public void onGameOver(Player player) {
        gameOver = true;
        gameOverController.showGameOverAlert();
    }

    @Override
    public void onDiceRolled(Player player, List<Integer> rolls) {
        int diceResult = rolls.stream().mapToInt(Integer::intValue).sum();
        boolean hasRolledDouble = rolls.get(0).equals(rolls.get(1));

        // If the player rolled a double, increment their double rolls count
        if (hasRolledDouble) {
            player.addDoubleRoll();
            // If the player has rolled doubles x times, send them to jail
            //TODO put remainingTurnsInPrison in settings
            if (player.getDoubleRolls() == game.getSettings().getDoubleRollsToJail()) {
                player.setRemainingTurnsInPrison(game.getSettings().getDoubleRollsToJail());
                player.resetDoubleRolls();
                Optional<Tile> jailTile = game.getGameState().getBoard().getTiles().stream()
                        .filter(tile -> tile.getType() == TileType.JAIL)
                        .findFirst();
                jailTile.ifPresent(tile -> player.getPosition().updatePosition(tile.getPosition()));

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
        int newPosition = (player.getPosition().getPos() + diceResult) % game.getGameState().getBoard().getTiles().size();
        int startPosition = game.getGameState().getBoard().getTiles().stream()
                .filter(tile -> tile.getType() == TileType.START)
                .map(Tile::getPosition)
                .findFirst()
                .orElse(0);

        //TODO fix passed start tile caculation.
        if (player.getPosition().getPos() - newPosition > startPosition) {
            System.out.println("turnhandler bonus");
            game.getBank().deposit(player, game.getSettings().getStartBonus());
            game.getLogBook().addEntry(new PassedStartLog(player));
        }

        Tile landedTile = game.getGameState().getBoard().getTiles().stream()
                .filter(tile -> tile.getPosition() == newPosition)
                .findFirst().get();

        if (landedTile.getType() == TileType.JAIL && player.getRemainingTurnsInPrison() > 0) {
            player.getInventory().useGetOutOfJailFreeCard();
        }

        if (player.getRemainingTurnsInPrison() > 0) {
            if (hasRolledDouble) {
                player.resetRemainingTurnsInPrison();
                player.getPosition().updatePosition(newPosition);
            } else {
                player.decreaseRemainingTurnsInPrison();
            }
        } else {
            player.getPosition().updatePosition(newPosition);
        }


        // Check if the player rolled a double if not disable the current player and enable the next player
        // Check if the player landed on the go to jail tile
        // if so go on to next player without letting throw second time
        boolean landedTileIsGoToJail = game.getGameState().getBoard().getTiles().stream().anyMatch(tile -> tile.getPosition() == newPosition && tile.getType() == TileType.GO_TO_JAIL);
        if (!hasRolledDouble || landedTileIsGoToJail) {
            playerManager.setPlayerPanelToInactive(getCurrentPlayer());
            currentPlayerIndex = (currentPlayerIndex + 1) % playerManager.getPlayers().size();
            playerManager.setPlayerPanelToActive(getCurrentPlayer());
        }

        playerManager.setPlayerPanelToActive(getCurrentPlayer());
    }
}