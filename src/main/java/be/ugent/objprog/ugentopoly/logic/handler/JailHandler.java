package be.ugent.objprog.ugentopoly.logic.handler;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

import java.util.Optional;

public class JailHandler {
    public boolean handle(Player player, boolean hasRolledDouble) {
        Optional<Tile> landedTile = player.getGameManager().getBoardManager().getBoard().getTiles().stream()
                .filter(tile -> tile.getPosition() == player.getPosition().getPos())
                .findFirst();

        if (landedTile.isPresent() && landedTile.get().getType() == TileType.JAIL && player.getRemainingTurnsInPrison() > 0) {
            player.getInventory().useGetOutOfJailFreeCard();
        }

        if (player.getRemainingTurnsInPrison() > 0) {
            if (hasRolledDouble) {
                player.setInJail(false);
            } else {
                player.decreaseRemainingTurnsInPrison();
                return false;
            }
        }
        return true;
    }
}
