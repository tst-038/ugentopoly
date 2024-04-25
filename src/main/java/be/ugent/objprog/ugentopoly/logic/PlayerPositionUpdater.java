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

    public void update(Player player, int diceResult) {
        int newPosition = (player.getPosition().getPos() + diceResult) % player.getGameManager().getGameState().getBoard().getTiles().size();
        int startPosition = player.getGameManager().getGameState().getBoard().getTiles().stream()
                .filter(tile -> tile.getType() == TileType.START)
                .map(Tile::getPosition)
                .findFirst()
                .orElse(0);

        int oldPos = player.getPosition().getPos();
        if (oldPos < startPosition && startPosition <= newPosition) {
            bank.deposit(player, settings.getStartBonus(), false);
            gameLogBook.addEntry(new PassedStartEvent(player));
        }

        player.getGameManager().getTileInfoPaneManager().setPaneClosableAndHide();
        player.getPosition().updatePosition(newPosition);
    }
}
