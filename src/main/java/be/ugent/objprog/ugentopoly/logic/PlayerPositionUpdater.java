package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PassedStartLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

public class PlayerPositionUpdater {
    private final GameLogBook gameLogBook;
    private final Bank bank;
    private final Settings settings;

    public PlayerPositionUpdater(GameLogBook gameLogBook, Bank bank, Settings settings) {
        this.gameLogBook = gameLogBook;
        this.bank = bank;
        this.settings = settings;
    }

    public void update(Player player, int diceResult) {
        int newPosition = (player.getPosition().getPos() + diceResult) % player.getGame().getGameState().getBoard().getTiles().size();
        int startPosition = player.getGame().getGameState().getBoard().getTiles().stream()
                .filter(tile -> tile.getType() == TileType.START)
                .map(Tile::getPosition)
                .findFirst()
                .orElse(0);

        int oldPos = player.getPosition().getPos();
        if (oldPos < startPosition && startPosition <= newPosition) {
            bank.deposit(player, settings.getStartBonus());
            gameLogBook.addEntry(new PassedStartLog(player));
        }

        player.getGame().getTileInfoPaneManager().setPaneClosableAndHide();
        player.getPosition().updatePosition(newPosition);
    }
}
