package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

public class GoToJailHandler {
    public boolean handle(Player player) {
        if(player.getGame().getGameState().getBoard().getTiles().stream()
                .anyMatch(tile -> tile.getPosition() == player.getPosition().getPos() && tile.getType() == TileType.GO_TO_JAIL)){
            player.setRemainingTurnsInPrison(3);
            return true;
        }
        return false;
    }
}
