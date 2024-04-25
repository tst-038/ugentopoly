package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

import java.util.Optional;

public class JailHandler {
    public boolean handle(Player player, boolean hasRolledDouble) {
        Optional<Tile> landedTile = player.getGame().getGameState().getBoard().getTiles().stream()
                .filter(tile -> tile.getPosition() == player.getPosition().getPos())
                .findFirst();

        if (landedTile.isPresent() && landedTile.get().getType() == TileType.JAIL && player.getRemainingTurnsInPrison() > 0) {
            player.getInventory().useGetOutOfJailFreeCard();
        }

        if (player.getRemainingTurnsInPrison() > 0) {
            if (hasRolledDouble) {
                player.resetRemainingTurnsInPrison();
            } else {
                player.decreaseRemainingTurnsInPrison();
                return false;
            }
        }
        return true;
    }
}
