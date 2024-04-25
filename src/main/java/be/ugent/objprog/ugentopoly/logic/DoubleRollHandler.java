package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

import java.util.Optional;

public class DoubleRollHandler {
    private final Settings settings;

    public DoubleRollHandler(Settings settings) {
        this.settings = settings;
    }

    public boolean handleDoubleRoll(Player player, boolean hasRolledDouble) {
        if (hasRolledDouble) {
            player.addDoubleRoll();
            if (player.getDoubleRolls() == settings.getDoubleRollsToJail()) {
                player.setRemainingTurnsInPrison(settings.getDoubleRollsToJail());
                player.resetDoubleRolls();
                Optional<Tile> jailTile = player.getGame().getGameState().getBoard().getTiles().stream()
                        .filter(tile -> tile.getType() == TileType.JAIL)
                        .findFirst();
                jailTile.ifPresent(tile -> player.getPosition().updatePosition(tile.getPosition()));
                return true;
            }
        } else {
            player.resetDoubleRolls();
        }
        return false;
    }
}