package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.event.PassedStartEvent;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

public class PlayerPositionUpdater {
    private final GameLogBook gameLogBook;
    private final Bank bank;
    private final Settings settings;

    public PlayerPositionUpdater(GameLogBook gameLogBook, Bank bank, Settings settings) {
        this.gameLogBook = gameLogBook;
        this.bank = bank;
        this.settings = settings;
    }

    public void update(Player player, int amount) {
        int boardSize = player.getGameManager().getGameState().getBoard().getTiles().size();
        int newPosition = (player.getPosition().getPos() + amount) % boardSize;
        int startPosition = player.getGameManager().getGameState().getBoard().getTiles().stream()
                .filter(tile -> tile.getType() == TileType.START)
                .map(Tile::getPosition)
                .findFirst()
                .orElse(0);

        int oldPos = player.getPosition().getPos();

        if (hasPassedStart(oldPos, newPosition, startPosition, boardSize, amount >= 0)){
            System.out.println("Player " + player.getName() + " has passed start");
            bank.deposit(player, settings.getStartBonus(), true);
            gameLogBook.addEntry(new PassedStartEvent(player));
        }

        player.getGameManager().getTileInfoPaneManager().setPaneClosableAndHide();
        player.getPosition().updatePosition(newPosition);
    }

    public boolean hasPassedStart(int oldPosition, int newPosition, int startTile, int totalTiles, boolean isMovingForward){
        if (oldPosition == startTile && newPosition != startTile) {
            return false;
        }

        if (isMovingForward) {
            int distance = (newPosition - oldPosition + totalTiles) % totalTiles;
            int distanceToStart = (startTile - oldPosition + totalTiles) % totalTiles;
            return distance >= distanceToStart;
        } else {
            return false;
        }
    }
}
