package be.ugent.objprog.ugentopoly.logic.handler;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

public class GoToJailHandler {
    public boolean handle(Player player) {
        if(player.getGameManager().getBoardManager().getBoard().getTiles().stream()
                .anyMatch(tile -> tile.getPosition() == player.getPosition().getPos() && tile.getType() == TileType.GO_TO_JAIL)){
            player.setRemainingTurnsInPrison(player.getGameManager().getSettings().getTurnsInJail());
            return true;
        }
        return false;
    }
}
