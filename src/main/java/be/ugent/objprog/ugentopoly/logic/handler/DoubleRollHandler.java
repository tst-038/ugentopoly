package be.ugent.objprog.ugentopoly.logic.handler;

import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

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
                Optional<Tile> jailTile = player.getGameManager().getBoardManager().getBoard().getTiles().stream()
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